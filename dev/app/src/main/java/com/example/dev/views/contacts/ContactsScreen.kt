package com.example.dev.views.contacts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dev.R
import com.example.dev.models.Contact

@Composable
fun ContactsScreen(navController: NavController, contacts: List<Contact>) {
    Scaffold (
        topBar = { ContactsTopBar(navController) },
        content = { innerPadding -> Box(modifier = Modifier.padding(innerPadding)) {
            ContactList(navController, contacts)
        }}
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
    ContactsScreen(
        navController = rememberNavController(),
        contacts = sample_contacts
    )
}