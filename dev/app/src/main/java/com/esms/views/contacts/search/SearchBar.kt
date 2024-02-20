package com.esms.views.contacts.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(state: MutableState<Boolean>, filterString: MutableState<String>) {
    val padding = 8.dp
    if(state.value){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface)
                .height(60.dp)
                .padding(padding)
        ) {
            SearchButton(state, filterString)
            BasicTextField(
                value = filterString.value,
                onValueChange = {filterString.value = it.lowercase()},
                textStyle = TextStyle(color = MaterialTheme.colors.onBackground),
                modifier = Modifier
                    .background(
                        shape = RoundedCornerShape(padding),
                        color = MaterialTheme.colors.background
                    )
                    .fillMaxWidth()
                    .padding(padding),
            )
        }
    }
}