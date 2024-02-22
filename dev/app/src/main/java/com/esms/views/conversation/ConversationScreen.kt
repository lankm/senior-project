package com.esms.views.conversation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.esms.views.conversation.history.ConversationHistory

@Composable
fun ConversationScreen(navController: NavController) {
    Scaffold (
        topBar = { ConversationTopBar(navController) },
        content = { innerPadding -> Box(modifier = Modifier.padding(innerPadding)) {
            ConversationHistory()
        }},
        bottomBar = {MessageInput(LocalContext.current)}
    )
}
