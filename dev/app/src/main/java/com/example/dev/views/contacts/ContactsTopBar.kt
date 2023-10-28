package com.example.dev.views.contacts

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ContactsTopBar(navController: NavController) {
    // No composable wrapper needed. Implemented in TopBar.kt
    
    // Left IconButton
    IconButton(
        onClick = {
            // TODO: implement adding a contact. probably a modal
        },
        modifier = Modifier.size(48.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add contact",
        )
    }

    // Center content
    Text(text = "ESMS", color = MaterialTheme.colors.onSurface)

    // Right IconButton
    IconButton(
        onClick = {
            // TODO: implement global settings. probably a right drawer
        },
        modifier = Modifier.size(48.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Global settings",
        )
    }
}