package com.esms.views.conversation.history

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.esms.models.LocalParameters
import com.esms.models.parseDate

@Composable
fun MessageBox(content: String,
               time: Long,
               received: Boolean,
) {
    val INCORRECT_KEY = "System message: <You encryption key is incorrect>"

    val params = LocalParameters.current
    var encrypted by remember { mutableStateOf(true) }
    val encryptedText by remember { mutableStateOf(content) }
    var decryptedText by remember { mutableStateOf<String?>(null) }

    // modifier values
    val layoutDirection: LayoutDirection = if (received) LayoutDirection.Ltr
                                                    else LayoutDirection.Rtl
    val backgroundColor: Color =           if (received) MaterialTheme.colors.secondary
                                                    else MaterialTheme.colors.surface
    val textColor: Color =                 if (received) MaterialTheme.colors.onSecondary
                                                    else MaterialTheme.colors.onSurface
    val fontSize = 15.sp
    val padding = 10.dp

    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .fillMaxWidth()
                .padding(padding, padding/5)
        ) {
            // message contents
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(
                        shape = RoundedCornerShape(padding),
                        color = backgroundColor
                    ).weight(
                        weight = 1.0f,  // limit max size
                        fill = false  // only grow if needed
                    )
            ) {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                Text(
                    text = if(encrypted) encryptedText else decryptedText ?: INCORRECT_KEY,
                    fontSize = fontSize,
                    textAlign = TextAlign.Left,
                    color = textColor,
                    modifier = Modifier
                        .padding(padding)
                        .clickable {
                            if(encrypted) {
                                if(decryptedText == null) {
                                    decryptedText = try {
                                        params.currentEncryptionEngine.value.decrypt(encryptedText)
                                    } catch (_:Exception){ null }
                                }
                                encrypted = false
                            } else {
                                encrypted = true
                            }
                        }
                    )
                }
            }

            // time stamp
            Column(
                verticalArrangement = Arrangement.Bottom,
            ) {
                Text(
                    text = time.parseDate().split(" ")[1],
                    fontSize = fontSize,
                    textAlign = if (layoutDirection == LayoutDirection.Ltr) TextAlign.Left else TextAlign.Right,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.padding(padding)
                )
            }
        }
    }
}
