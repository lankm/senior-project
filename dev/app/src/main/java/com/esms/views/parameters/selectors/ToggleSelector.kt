package com.esms.views.parameters.selectors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun ToggleSelector(
    name: String,
    setter: (Boolean)->Unit,
    currentState: Boolean,
): @Composable () -> Unit{
    return {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            // general contact info
            Box(
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = name,
                    color = MaterialTheme.colors.onSurface
                )
            }

            Box{
                var state by remember { mutableStateOf(currentState) }
                Switch(checked = currentState, onCheckedChange = {state = it; setter(it)})
            }
        }
    }
}