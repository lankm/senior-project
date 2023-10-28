package com.example.dev.views.contacts

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dev.models.Contact

@Composable
fun ContactList(contacts: List<Contact>) {
    val scrollState = rememberLazyListState()

    // Create the LazyColumn
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = scrollState
    ) {
        contacts.forEach { contact ->
            item {
                ContactBox(contact)
            }
        }
    }
}

@Preview
@Composable
fun ContactListPreview() {
    ContactList(sample_contacts)
}