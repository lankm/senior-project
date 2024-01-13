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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.esms.models.Parameters
import com.esms.models.parseDate

@Composable
fun MessageBox(content: String,
               time: Long,
               received: Boolean,
               params: Parameters
) {
    val encrypted = remember { mutableStateOf(true) }
    val storedText = remember { mutableStateOf(content) }

    // modifier values
    val layoutDirection: LayoutDirection = if (received) LayoutDirection.Ltr
                                                    else LayoutDirection.Rtl
    val backgroundColor: Color =           if (received) MaterialTheme.colors.secondary
                                                    else MaterialTheme.colors.surface
    val textColor: Color =                 if (received) MaterialTheme.colors.onSecondary
                                                    else MaterialTheme.colors.onSurface
    // TODO: change all sp/dp values to depend on the phone model
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
                    text = storedText.value,
                    fontSize = fontSize,
                    textAlign = TextAlign.Left,
                    color = textColor,
                    modifier = Modifier
                        .padding(padding)
                        .clickable {
                        if(encrypted.value) {
                            try {
                                storedText.value =
                                    params.currentEncryptionEngine.value.decrypt(storedText.value)
                            } catch (_:Exception){}
                            encrypted.value = false
                        } else {
                            storedText.value = params.currentEncryptionEngine.value.encrypt(storedText.value)
                            encrypted.value = true
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
