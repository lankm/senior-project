package com.example.dev.views.messages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dev.R
import com.example.dev.models.Contact
import com.example.dev.views.contacts.ContactBox
import com.example.dev.views.contacts.sample_contacts

// TODO: this is temporary, refactor by combining contacts/messages scaffolds
@Composable
fun ContactsTopBar() {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .padding(5.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add contact",
        )

        Text(text = "ESMS", color = MaterialTheme.colors.onSurface)

        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Global settings",
        )
    }
}



@Preview
@Composable
fun ContactsTopBarPreview() {
    ContactsTopBar()
}