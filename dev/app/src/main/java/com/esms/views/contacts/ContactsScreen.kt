package com.esms.views.contacts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.esms.models.LocalParameters

@Composable
fun ContactsScreen(navController: NavController) {
    val params = LocalParameters.current
    val filterString = remember { mutableStateOf("") }
    Scaffold (
        topBar = { ContactsTopBar(navController, filterString) },
        content = { innerPadding -> Box(modifier = Modifier.padding(innerPadding)) {
            ContactList(navController, filterString)
        }}
    )
}