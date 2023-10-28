package com.example.dev.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.dev.views.contacts.ContactsTopBar
import com.example.dev.views.contacts.ConversationTopBar

@Composable
fun TopBar(navController: NavController) {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .height(60.dp)
            .padding(5.dp)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        when ( navBackStackEntry?.destination?.route ) {
            "contacts" -> {
                ContactsTopBar(navController)
            }
            "conversation" -> {
                ConversationTopBar(navController)
            }
        }
    }
}