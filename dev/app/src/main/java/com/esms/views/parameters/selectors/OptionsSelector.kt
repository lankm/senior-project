package com.esms.views.parameters.selectors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun OptionsSelector(
    name: String,
    setter: (String)->Unit,
    currentState: String,
    options: List<String>,
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
                var expanded by remember { mutableStateOf(false) }
                var state by remember { mutableStateOf(currentState) }
                Button(onClick = { expanded = true }) {
                    Text(state)
                }
                DropdownMenu(expanded = expanded, onDismissRequest = {expanded = false}) {
                    options.forEach { option ->
                        DropdownMenuItem(onClick = {
                            setter(option)
                            state = option
                            expanded = false
                        }) {
                            Text(text = option)
                        }
                    }
                }
            }
        }
    }
}