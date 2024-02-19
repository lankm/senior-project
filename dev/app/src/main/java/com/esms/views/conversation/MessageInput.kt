package com.esms.views.conversation

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.esms.models.Parameters
import com.esms.models.SMSMessage
import com.esms.services.SmsService
import kotlin.concurrent.thread

@Composable
fun MessageInput(context: Context, params: Parameters) {
    val currentContact = remember {params.currentContact.value!!}
    val currentAddress = currentContact.number
    var text by remember { mutableStateOf("") }
    val padding = 8.dp

    Row (
        modifier = Modifier.padding(padding),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
            modifier = Modifier
                .background(
                    shape = RoundedCornerShape(7.dp),
                    color = MaterialTheme.colors.surface
                )
                .fillMaxWidth(0.90F)
                .padding(padding),
            maxLines = 5,
        )

        // Send IconButton
        IconButton(
            onClick = {
                if(text.isNotEmpty()) {
                    val messageText = text
                    text = ""
                    sendMessageAsync(context.applicationContext, params, messageText, currentAddress)
                }
            },
            modifier = Modifier
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Send message",
                tint = MaterialTheme.colors.onBackground
            )
        }
    }
}

private fun sendMessageAsync(context: Context, params: Parameters, text: String, currentAddress: String) {
    val encryptedText = params.currentEncryptionEngine.value.encrypt(text)
    params.runCurrentMessageAdder(
        SMSMessage(
            body = encryptedText,
            extAddr = currentAddress,
            date = System.currentTimeMillis(),
            read = true,
            type = SMSMessage.SENT,
            thread = 0
        )
    )
    thread(start = true) {
        SmsService(context).sendMessage(
            currentAddress,
            encryptedText
        )}
}