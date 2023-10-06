package com.example.dev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dev.ui.theme.DevTheme

import androidx.navigation.compose.*
import androidx.navigation.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DevTheme { // TODO: research themes
                val navController = rememberNavController()

                Scaffold (
                    content = { it  // TODO: learn why this is forced
                         Navigation(navController = navController)
                    },
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    name = "Contacts",
                                    route = "contacts",
                                    icon = Icons.Default.Person
                                ),
                                BottomNavItem(
                                    name = "Messages",
                                    route = "messages",
                                    icon = Icons.Default.Send
                                ),
                                BottomNavItem(
                                    name = "Settings",
                                    route = "settings",
                                    icon = Icons.Default.Settings
                                )
                            ),
                            navController = navController,
                            onItemCLick = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "messages") {
        composable("contacts") {
            ContactsScreen()
        }
        composable("messages") {
            MessagesScreen()
        }
        composable("settings") {
            SettingsScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemCLick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.Gray, // TODO: refactor to come from a central location for theme
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                selectedContentColor = Color.DarkGray,    // TODO: refactor to come from a central location for theme
                unselectedContentColor = Color.LightGray, // TODO: refactor to come from a central location for theme

                onClick = { onItemCLick(item) },
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        if(item.badgeCount > 0) {
                            BadgedBox(
                                badge = { Badge { Text(text = item.badgeCount.toString()) } }
                            ) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.name
                                )
                            }
                        } else {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.name
                            )
                        }
                        if(selected) {
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            )
        }
    }
}

// TODO: refactor into separate files and directories
@Composable
fun ContactsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Contacts")
    }
}
@Composable
fun MessagesScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Messages")
    }
}
@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Settings")
    }
}
