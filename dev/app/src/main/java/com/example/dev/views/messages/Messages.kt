package com.example.dev.views.messages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dev.models.Message
import com.example.dev.views.messages.history.MessageBox
import com.example.dev.views.messages.history.MessageHistory
import java.time.LocalTime

@Composable
fun MessagesScreen(recipient: String, messageHistory: List<Message>) {
    Scaffold(
        topBar = {
            MessagesTopBar(
                recipient = recipient
            )
        },
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                MessageHistory(messageHistory)
            }
        },
        bottomBar = {
            MessageInput()
        }
    )
}

// TODO: remove this
val sample_messages = listOf(
    Message(
        content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
        time = LocalTime.now().minusMinutes(56),
        recieved = true
    ),
    Message(
        content = "adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        time = LocalTime.now().minusMinutes(30),
        recieved = false
    ),
    Message(
        content = ":)",
        time = LocalTime.now().minusMinutes(20),
        recieved = true
    ),
    Message(
        content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
        time = LocalTime.now().minusMinutes(16),
        recieved = false
    ),
    Message(
        content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
        time = LocalTime.now().minusMinutes(10),
        recieved = false
    ),
    Message(
        content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
        time = LocalTime.now().minusMinutes(2),
        recieved = true
    ),
)


@Preview
@Composable
fun MessagesScreenPreview() {
    MessagesScreen("Landon Moon", sample_messages)
}
