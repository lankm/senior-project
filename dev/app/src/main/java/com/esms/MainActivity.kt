package com.esms

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.esms.theme.EsmsTheme

import androidx.navigation.compose.*
import androidx.navigation.*
import com.esms.models.LocalParameters
import com.esms.models.Parameters
import com.esms.views.AuthScreen
import com.esms.views.PermissionsScreen
import com.esms.views.contacts.ContactsScreen
import com.esms.views.conversation.ConversationScreen
import com.esms.views.parameters.ParametersScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val params = viewModel<Parameters>()
            CompositionLocalProvider(LocalParameters provides params) {
                EsmsTheme {
                    val navController = rememberNavController()
                    Navigation(navController)
                }
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
                },
            )
        }
        navigation(startDestination = "contacts", route = "application") {
            composable("contacts") {
                ContactsScreen(
                    navController = navController,
                )
            }
            composable("conversation") {
                ConversationScreen(
                    navController = navController,
                )
            }
            composable("parameters") {
                ParametersScreen(
                    navController = navController,
                )
            }
        }
    }
}