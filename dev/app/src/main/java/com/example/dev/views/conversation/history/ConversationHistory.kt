package com.example.dev.views.conversation.history

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dev.models.Message
import com.example.dev.views.conversation.sample_messages


@Composable
fun ConversationHistory(messages: List<Message>) {
    val scrollState = rememberLazyListState(messages.size - 1)

    // Create the LazyColumn
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = scrollState
    ) {
        messages.forEach { message ->
            item {
                MessageBox(message = message)
            }
        }
    }
}

@Preview
@Composable
fun ConversationHistoryPreview() {
    ConversationHistory(sample_messages)
}