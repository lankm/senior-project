package com.esms.views.parameters.selectors

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

fun SectionMarker(
    text: String,
    isNull: Boolean = false
): (@Composable () -> Unit)?{
    if (isNull)
        return null
    return {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(text = text,
                textAlign = TextAlign.Center,
                fontSize = LocalTextStyle.current.fontSize.times(1.4F),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Divider(
            thickness = 2.dp,
            color = MaterialTheme.colors.onBackground
        )
    }
}