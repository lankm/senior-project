package com.esms.views.conversation

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.esms.models.Parameters
import com.esms.views.contacts.ContactBox
import com.esms.views.contacts.sampleContact

@Composable
fun ConversationTopBar(navController: NavController, params: Parameters) {
    val currentContact = remember {params.currentContact.value!!}
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .height(60.dp)
            .padding(5.dp)
    ) {
        // Left IconButton
        IconButton(
            onClick = {
                navController.popBackStack()
                params.currentContact.value = null
            },
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back to contacts",
            )
        }

        // Center Content
        ContactBox(contact = currentContact)

        // Right IconButton
        IconButton(
            onClick = { navController.navigate("parameters") },
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Conversation settings",
            )
        }
    }
}