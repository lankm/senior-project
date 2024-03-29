package com.esms.views.conversation.history

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import com.esms.models.LocalParameters
import com.esms.models.SMSMessage
import com.esms.models.parseDate
import com.esms.services.SmsListener
import com.esms.services.SmsService
import kotlin.math.max


@Composable
fun ConversationHistory() {
    val params = LocalParameters.current
    val currentContact = remember {params.currentContact.value!!}
    val currentAddress = currentContact.number
    val context = LocalContext.current
    val smsService = SmsService(context)
    var allMessages by remember { mutableStateOf(smsService.readMessages(currentAddress)) }
    val listHeight = remember { mutableIntStateOf(0)}
    params.setCurrentMessageAdder { msg: SMSMessage ->
        run {
            allMessages += msg
        }
    }
    val scrollState = rememberLazyListState(2 * allMessages.size)
    LaunchedEffect(allMessages.size) {
        scrollState.scrollToItem(index = max(2 * allMessages.size - 1, 0))
    }
    LaunchedEffect(listHeight.intValue) {
        scrollState.scrollToItem(index = max(2 * allMessages.size - 1, 0))
    }
    SmsListener { newMessage: SMSMessage -> run {
            val origin = newMessage.extAddr.replace(Regex("[)(+\\- ]"),"")
            if ( origin.substring(origin.length-10) in currentAddress.replace(Regex("[)(+\\- ]"), "")
            ) { // This will match numbers with different country codes and the same base 10 numbers
                allMessages += newMessage
            }
        }
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .onSizeChanged { size -> listHeight.intValue = size.height },
        state = scrollState
    ) {
        allMessages.groupBy { it.date.parseDate().split(" ").first() }
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
                        received = it.type == SMSMessage.RECEIVED,
                    )
                    params.setLastMessageTimeForNumber(currentContact.number, it.date)
                }
            }
    }
}
