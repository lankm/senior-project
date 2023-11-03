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
import com.example.dev.views.AuthScreen
import com.example.dev.views.PermissionsScreen
import com.example.dev.views.contacts.ContactsScreen
import com.example.dev.views.contacts.sample_contacts
import com.example.dev.views.conversation.ConversationScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EsmsTheme {
                val navController = rememberNavController()
                Navigation(navController)
            }
        }
    }
}

// NavGraph
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "permissions") {
        composable("permissions") {
            PermissionsScreen(
                onPermissionGranted = {
                    navController.popBackStack()
                    navController.navigate("auth")
                }
            )
        }
        composable("auth") {
            AuthScreen(
                onAuthGranted = {
                    navController.popBackStack()
                    navController.navigate("application")
                }
            )
        }
        navigation(startDestination = "contacts", route = "application") {
            composable("contacts") {
                ContactsScreen(
                    navController = navController,
                    contacts = sample_contacts
                )
            }
            composable("conversation") {
                ConversationScreen(
                    navController = navController,
                    contact = sample_contacts[0]
                )
            }
        }
    }
}