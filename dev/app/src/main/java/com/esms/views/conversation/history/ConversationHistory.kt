package com.esms.views.conversation.history

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.esms.models.Parameters
import com.esms.models.SMSMessage
import com.esms.models.parseDate
import com.esms.services.SmsService


@Composable
fun ConversationHistory(params: Parameters) {
    val currentAddress = params.currentContact.value.number
    val context = LocalContext.current
    val smsService = SmsService(context)
    val allMessages: List<SMSMessage> = remember { smsService.readMessages(currentAddress) }

    val scrollState = rememberLazyListState(2 * allMessages.size)
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
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
                    key = {it.date}
                ) {
                    println(it.body)
                    MessageBox(
                        content = it.body,
                        time = it.date,
                        recieved = it.type == 1,
                        params = params
                    )
                }
            }
    }
}

@Preview
@Composable
fun ConversationHistoryPreview() {
    ConversationHistory(Parameters())
}