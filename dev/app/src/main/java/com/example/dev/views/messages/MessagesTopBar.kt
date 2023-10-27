package com.example.dev.views.messages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MessagesTopBar(recipient: String) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colors.primary)
        .padding(5.dp)
    ) {
        Text(
            text = recipient,
            color = MaterialTheme.colors.onPrimary
        )
    }
}



@Preview
@Composable
fun MessagesTopBarPreview() {
    MessagesTopBar("Landon Moon")
}