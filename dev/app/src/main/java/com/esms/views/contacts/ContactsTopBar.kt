package com.esms.views.contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.esms.views.contacts.search.SearchBar
import com.esms.views.contacts.search.SearchButton

@Composable
fun ContactsTopBar(navController: NavController, filterString: MutableState<String>) {
    val searchBarState = remember { mutableStateOf(false) }
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .height(60.dp)
            .padding(5.dp)
    ) {
        // Left IconButton
        SearchButton(searchBarState)

        // Center content
        Text(text = "ESMS", color = MaterialTheme.colors.onSurface)

        // Right IconButton
        IconButton(
            onClick = { navController.navigate("parameters") },
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Global settings",
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
    SearchBar(searchBarState, filterString)
}