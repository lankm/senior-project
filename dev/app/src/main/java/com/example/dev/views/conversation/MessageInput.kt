package com.example.dev.views.conversation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun MessageInput() {
    var text by remember { mutableStateOf("") }

    BasicTextField(
        value = text,
        onValueChange = { text = it },
        textStyle = TextStyle(color = MaterialTheme.colors.secondary),
        modifier = Modifier
            .background(color = MaterialTheme.colors.surface)
            .fillMaxWidth()
            .padding(10.dp),
    )
}