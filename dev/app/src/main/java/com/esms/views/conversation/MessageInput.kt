package com.esms.views.conversation

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.esms.models.Parameters
import com.esms.services.SmsService

@Composable
fun MessageInput(context: Context, params: Parameters) {
    val currentAddress = params.currentAddress.value
    var text by remember { mutableStateOf("") }
    val padding = 10.dp
    
    Row (modifier = Modifier.padding(padding, padding), horizontalArrangement = Arrangement.SpaceBetween) {
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            textStyle = TextStyle(color = MaterialTheme.colors.secondary),
            modifier = Modifier
                .background(
                    shape = RoundedCornerShape(padding),
                    color = MaterialTheme.colors.surface
                )
                .fillMaxWidth(0.90F)
                .fillMaxHeight(.05f)
                .padding(padding),
        )

        // Send IconButton
        IconButton(
            onClick = {if(text.length > 0)SmsService(context).sendMessage(
                currentAddress,
                params.currentEncryptionEngine.value.encrypt(text)
            )
                      text = ""},
            modifier = Modifier.fillMaxHeight(.05f)
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Send message",
            )
        }
    }
}