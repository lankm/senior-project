package com.example.dev.views.contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ContactsScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colors.background),   // used for preview. Redundant otherwise
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Contacts")
    }
}


@Preview
@Composable
fun ContactsScreenPreview() {
    ContactsScreen()
}