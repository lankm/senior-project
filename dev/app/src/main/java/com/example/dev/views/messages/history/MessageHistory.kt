package com.example.dev.views.messages.history

import android.content.ClipData.Item
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dev.models.Message
import com.example.dev.views.messages.MessagesTopBar
import com.example.dev.views.messages.sample_messages


@Composable
fun MessageHistory(messages: List<Message>) {
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
fun MessageHistoryPreview() {
    MessageHistory(sample_messages)
}