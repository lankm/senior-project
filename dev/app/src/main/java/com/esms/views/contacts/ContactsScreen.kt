package com.esms.views.contacts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.esms.models.Parameters

@Composable
fun ContactsScreen(navController: NavController, params: Parameters) {
    val filterString = remember { mutableStateOf("") }
    Scaffold (
        topBar = { ContactsTopBar(navController, filterString) },
        content = { innerPadding -> Box(modifier = Modifier.padding(innerPadding)) {
            ContactList(navController, params, filterString)
        }}
    )
}

@Preview
@Composable
fun ContactsScreenPreview() {
    ContactsScreen(
        navController = rememberNavController(), Parameters(LocalContext.current)
    )
}