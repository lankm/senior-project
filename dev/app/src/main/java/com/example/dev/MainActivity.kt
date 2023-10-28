package com.example.dev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.dev.theme.EsmsTheme

import androidx.navigation.compose.*
import androidx.navigation.*
import com.example.dev.views.TopBar
import com.example.dev.views.contacts.ContactsScreen
import com.example.dev.views.contacts.sample_contacts
import com.example.dev.views.conversation.ConversationScreen
import com.example.dev.views.conversation.sample_messages

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EsmsTheme {
                val navController = rememberNavController()
                Scaffold (
                    topBar = {
                        TopBar(navController)
                    },
                    content = { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            Navigation(navController = navController)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "contacts") {
        composable("contacts") {
            ContactsScreen(
                navController = navController,
                contacts = sample_contacts
            )
        }
        composable("conversation") {
            ConversationScreen(
                contact = sample_contacts[0],
                messageHistory = sample_messages
            )
        }
    }
}