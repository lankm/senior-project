package com.example.dev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dev.ui.theme.DevTheme

import androidx.navigation.compose.*
import androidx.navigation.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DevTheme { // TODO: research themes
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "login") {
                    navigation(
                        startDestination = "messages",
                        route = "comm"
                    ) {
                        composable("contacts") {
                            val viewModel = it.sharedViewModel<SampleViewModel>(navController = navController)

                            Column {
                                Text("Contacts")
                                Button(onClick = {
                                    navController.navigate("messages") {
                                        popUpTo("comm") {
                                            inclusive = true } } } ) {}
                            }
                        }
                        composable("messages") {
                            val viewModel = it.sharedViewModel<SampleViewModel>(navController = navController)

                            Column {
                                Text("Messages")
                                Button(onClick = {
                                    navController.navigate("settings") {
                                        popUpTo("comm") {
                                            inclusive = true } } } ) {}
                            }
                        }
                        composable("settings") {
                            val viewModel = it.sharedViewModel<SampleViewModel>(navController = navController)

                            Column {
                                Text("Settings")
                                Button(onClick = {
                                    navController.navigate("contacts") {
                                        popUpTo("comm") {
                                            inclusive = true } } } ) {}
                            }
                        }
                    }
                    composable("login") {
                        Column {
                            Text("Login")
                            Button(onClick = {
                                navController.navigate("comm") {
                                    popUpTo("login") {
                                        inclusive = true
                                    }
                                }
                            }) {

                            }
                        }

                    }
                }
            }
        }
    }
}

@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}

// temporary ==============================

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .background(Color.Gray)
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hello $name!",
        )
        Text(
            text = "Some other text!",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DevTheme {
        Greeting("Android")
    }
}