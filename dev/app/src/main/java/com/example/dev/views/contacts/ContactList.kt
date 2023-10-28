package com.example.dev.views.contacts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dev.models.Contact

@Composable
fun ContactList(navController: NavController, contacts: List<Contact>) {
    val scrollState = rememberLazyListState()

    // Create the LazyColumn
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = scrollState
    ) {
        contacts.forEach { contact ->
            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    // general contact info
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .clickable {
                                navController.navigate("conversation")// TODO: navigate to a specific contact conversation
                            }
                    ) {
                        ContactBox(contact)
                    }


                    // IconButton to edit/get more info
                    IconButton(
                        onClick = {
                            // TODO: implement contact editing
                        },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit ${contact.name}'s contact info",
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ContactListPreview() {
    ContactList(
        navController = rememberNavController(),
        contacts = sample_contacts
    )
}