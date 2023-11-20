package com.esms.views.conversation

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.esms.services.SmsService

@Composable
fun MessageInput(context: Context) {
    var text by remember { mutableStateOf("") }
    
    Row (horizontalArrangement = Arrangement.SpaceBetween) {
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            textStyle = TextStyle(color = MaterialTheme.colors.secondary),
            modifier = Modifier
                .background(color = MaterialTheme.colors.surface)
                .fillMaxWidth(0.9F)
                .padding(10.dp),
        )

        // Send IconButton
        IconButton(
            onClick = {SmsService(context).sendMessage("8173729003", text)},
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Send message",
            )
        }
    }
}