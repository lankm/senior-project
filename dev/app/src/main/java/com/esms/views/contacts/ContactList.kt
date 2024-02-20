package com.esms.views.contacts

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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.esms.models.LocalParameters
import com.esms.models.PhoneContact
import com.esms.services.readContacts

@Composable
fun ContactList(navController: NavController, filterString: MutableState<String>) {
    val params = LocalParameters.current
    val context = LocalContext.current
    // retrieve the list of contacts
    val allContacts = remember { mutableListOf<PhoneContact>() }
    LaunchedEffect(key1 = Unit) {
        val contact = readContacts(context = context)
        allContacts += contact.sortedBy { -params.getLastMessageTimeFor(it.number) }
    }

    // display the list of contacts
    val scrollState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = scrollState
    ) {
        allContacts.filter { params.getNicknameFor(it.number, it.name).lowercase().contains(filterString.value) }.forEach { contact ->
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
                                params.currentContact.value = contact
                                params.setCurrentEncryptionEngine(contact.number)
                                navController.navigate("conversation")
                            }
                    ) {
                        ContactBox(contact)
                    }


                    // IconButton to edit/get more info
                    IconButton(
                        onClick = {
                            params.currentContact.value = contact
                            navController.navigate("parameters")
                        },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit ${contact.name}'s contact info",
                            tint = MaterialTheme.colors.onSurface
                        )
                    }
                }
            }
        }
    }
}