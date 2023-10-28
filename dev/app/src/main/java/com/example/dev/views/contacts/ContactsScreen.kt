package com.example.dev.views.contacts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dev.R
import com.example.dev.models.Contact
import com.example.dev.views.messages.ContactsTopBar
import com.example.dev.views.messages.MessageInput
import com.example.dev.views.messages.MessagesTopBar
import com.example.dev.views.messages.history.MessageHistory

@Composable
fun ContactsScreen(contacts: List<Contact>) {
    Scaffold(
        topBar = {
            ContactsTopBar() //TODO: combine into a single scaffold topbar with messages screen
        },
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                ContactList(contacts)
            }
        }
    )


}

// TODO: Delete this
val sample_contacts = listOf(
    Contact(R.drawable.ic_launcher_background, "Landon Moon", "817-694-6767"),
    Contact(R.drawable.ic_launcher_background, "Jacob Holz", "696-969-6969"),
    Contact(R.drawable.ic_launcher_background, "Parker Steach", "999-999-9999"),
    Contact(R.drawable.ic_launcher_background, "Gilbert Lavin", "888-888-8888"),
    Contact(R.drawable.ic_launcher_background, "Nam Huynh", "817-694-6767"),
    Contact(R.drawable.ic_launcher_background, "Landon Moon again", "817-694-6767"),
    Contact(R.drawable.ic_launcher_background, "Jacob Holz  again", "696-969-6969"),
    Contact(R.drawable.ic_launcher_background, "Parker Steach  again", "999-999-9999"),
    Contact(R.drawable.ic_launcher_background, "Gilbert Lavin  again", "888-888-8888"),
    Contact(R.drawable.ic_launcher_background, "Nam Huynh  again", "817-694-6767"),
)


@Preview
@Composable
fun ContactsScreenPreview() {
    ContactsScreen(sample_contacts)
}