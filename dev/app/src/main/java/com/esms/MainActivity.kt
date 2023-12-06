package com.esms

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.esms.theme.EsmsTheme

import androidx.navigation.compose.*
import androidx.navigation.*
import com.esms.models.Parameters
import com.esms.views.AuthScreen
import com.esms.views.PermissionsScreen
import com.esms.views.contacts.ContactsScreen
import com.esms.views.conversation.ConversationScreen

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
    val params = Parameters()
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
                    params = params
                )
            }
            composable("conversation") {
                ConversationScreen(
                    navController = navController,
                    params = params
                )
            }
        }
    }
}