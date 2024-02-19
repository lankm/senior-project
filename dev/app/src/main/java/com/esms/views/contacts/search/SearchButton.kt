package com.esms.views.contacts.search

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

@Composable
fun SearchButton(state: MutableState<Boolean>, filterString: MutableState<String> = mutableStateOf("")) {
    IconButton(
        onClick = {
            if(filterString.value.isEmpty()) {
                state.value = !state.value
            } else {
                filterString.value = ""
            }
        },
    ) {
        Icon(
            imageVector = if(state.value) Icons.Default.Close else Icons.Default.Search,
            contentDescription = "Open contacts filter",
            tint = MaterialTheme.colors.onSurface
        )
    }
}