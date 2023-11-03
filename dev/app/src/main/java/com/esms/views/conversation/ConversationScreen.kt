package com.esms.views.conversation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.esms.models.Contact
import com.esms.views.contacts.sample_contacts
import com.esms.views.conversation.history.ConversationHistory

@Composable
fun ConversationScreen(navController: NavController, contact: Contact) {
    Scaffold (
        topBar = { ConversationTopBar(navController) },
        content = { innerPadding -> Box(modifier = Modifier.padding(innerPadding)) {
            ConversationHistory()
        }},
        bottomBar = { MessageInput() }
    )
}

@Preview
@Composable
fun MessagesScreenPreview() {
    ConversationScreen(
        navController = rememberNavController(),
        contact = sample_contacts[0]
    )
}
