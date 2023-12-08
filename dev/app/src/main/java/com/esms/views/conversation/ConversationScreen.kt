package com.esms.views.conversation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.esms.models.Parameters
import com.esms.views.conversation.history.ConversationHistory

@Composable
fun ConversationScreen(navController: NavController, params: Parameters) {
    Scaffold (
        topBar = { ConversationTopBar(navController, params) },
        content = { innerPadding -> Box(modifier = Modifier.padding(innerPadding)) {
            ConversationHistory(params)
        }},
        bottomBar = {MessageInput(LocalContext.current, params = params)}
    )
}

@Preview
@Composable
fun MessagesScreenPreview() {
    ConversationScreen(
        navController = rememberNavController(),
        params = Parameters()
    )
}
