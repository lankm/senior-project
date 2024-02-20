package com.esms.views.parameters.selectors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun FreeSelector(
    name: String,
    setter: (String)->Unit,
    currentState: String,
    comment: String = "",
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

            var showDialog by remember { mutableStateOf(false) }
            // IconButton to edit/get more info
            IconButton(
                onClick = { showDialog = true },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit $name",
                    tint = MaterialTheme.colors.onBackground
                )
            }
            var text by remember { mutableStateOf(currentState) }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text(text = name) },
                    text = {
                        TextField(
                            value = text,
                            onValueChange = { text = it },
                            label = { Text("Set value$comment") }
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                showDialog = false
                                setter(text)
                            }
                        ) { Text("Set") }
                    },
                    dismissButton = {
                        Button(
                            onClick = { showDialog = false },
                            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant)
                        ) { Text("Cancel", color = MaterialTheme.colors.onPrimary) }
                    }
                )
            }
        }
    }
}