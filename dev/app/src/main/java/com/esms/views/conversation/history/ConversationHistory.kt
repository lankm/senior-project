package com.esms.views.conversation.history

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.esms.models.Parameters
import com.esms.models.SMSMessage
import com.esms.models.parseDate
import com.esms.services.SmsListener
import com.esms.services.SmsService


@Composable
fun ConversationHistory(navController: NavController, params: Parameters) {
    val currentContact = remember {params.currentContact.value!!}
    val currentAddress = currentContact.number
    val context = LocalContext.current
    val smsService = SmsService(context)
    val allMessages = remember { mutableStateOf(smsService.readMessages(currentAddress)) }
    if(allMessages.value.isEmpty()) {
        navController.popBackStack()
        Toast.makeText(context, "No messages with ${params.getNicknameFor(currentAddress, currentContact.name)}", Toast.LENGTH_LONG).show()
        return
    } // Prevents no messages error.
    val listHeight = remember { mutableIntStateOf(0)}
    params.setCurrentMessageAdder { msg: SMSMessage ->
        run {
            allMessages.value += msg
        }
    }
    val scrollState = rememberLazyListState(2 * allMessages.value.size)
    LaunchedEffect(allMessages.value.size) {
        scrollState.scrollToItem(index = 2 * allMessages.value.size - 1)
    }
    LaunchedEffect(listHeight.intValue) {
        scrollState.scrollToItem(index = 2 * allMessages.value.size - 1)
    }
    SmsListener { newMessage: SMSMessage ->
        if (newMessage.extAddr.replace(Regex("[)(+\\- ]"), "") in currentAddress.replace(Regex("[)(+\\- ]"), "")) {
            allMessages.value += newMessage
        }
    }
    var index = 0
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .onSizeChanged { size -> listHeight.intValue = size.height },
        state = scrollState
    ) {
        allMessages.value.groupBy { it.date.parseDate().split(" ").first() }
            .forEach { (date, smsMessages) ->
                item {
                    val dateParts = date.split("/")
                    val month = dateParts[1]
                    val day = dateParts[0].replace(Regex("^0"), "")
                    val year = dateParts[2]
                    Text(
                        text = "$month $day, $year",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
                items(
                    items = smsMessages,
                ) {
                    MessageBox(
                        content = it.body,
                        time = it.date,
                        received = it.type == 1,
                        params = params
                    )
                    params.setLastMessageTimeFor(currentContact.number, it.date)
                }
            }
    }
}
