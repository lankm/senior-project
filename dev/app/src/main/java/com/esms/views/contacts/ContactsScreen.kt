package com.esms.views.contacts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ContactsScreen(navController: NavController) {
    Scaffold (
        topBar = { ContactsTopBar(navController) },
        content = { innerPadding -> Box(modifier = Modifier.padding(innerPadding)) {
            ContactList(navController)
        }}
    )
}

@Preview
@Composable
fun ContactsScreenPreview() {
    ContactsScreen(
        navController = rememberNavController(),
    )
}