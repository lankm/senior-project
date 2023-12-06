package com.esms.views.conversation.history

import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.esms.models.SMSMessage
import com.esms.models.parseDate
import com.esms.services.SmsService


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ConversationHistory(currentAddress: String = "") {
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
                    Text(
                        text = date,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

                items(
                    items = smsMessages,
                    key = {it.date}
                ) {
                    MessageBox(
                        content = it.body,
                        time = it.date,
                        recieved = it.type == 1
                    )
                }
            }
    }
}

@Preview
@Composable
fun ConversationHistoryPreview() {
    ConversationHistory()
}