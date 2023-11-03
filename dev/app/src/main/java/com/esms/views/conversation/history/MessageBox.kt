package com.esms.views.conversation.history

import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.esms.models.Message
import com.esms.models.parseDate
import java.time.LocalTime

@Composable
fun MessageBox(content: String,
               time: Long,
               recieved: Boolean
) {
    // modifier values
    val layoutDirection: LayoutDirection = if (recieved) LayoutDirection.Ltr
                                                    else LayoutDirection.Rtl
    val backgroundColor: Color =           if (recieved) MaterialTheme.colors.secondary
                                                    else MaterialTheme.colors.surface
    val textColor: Color =                 if (recieved) MaterialTheme.colors.onSecondary
                                                    else MaterialTheme.colors.onSurface
    // TODO: change all sp/dp values to depend on the phone model
    val fontsize = 15.sp
    val padding = 10.dp

    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
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
                Text(
                    text = content,
                    fontSize = fontsize,
                    textAlign = TextAlign.Left,
                    color = textColor,
                    modifier = Modifier.padding(padding)
                )
            }

            // time stamp
            Column(
                verticalArrangement = Arrangement.Bottom,
            ) {
                Text(
                    text = time.parseDate().split(" ")[1],
                    fontSize = fontsize,
                    textAlign = if (layoutDirection == LayoutDirection.Ltr) TextAlign.Left else TextAlign.Right,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.padding(padding)
                )
            }
        }
    }
}



@Preview
@Composable
fun MessagesBoxPreview() {
    val message = Message(
        content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
        time = LocalTime.now(),
        recieved = true
    )

//    MessageBox(message)
}