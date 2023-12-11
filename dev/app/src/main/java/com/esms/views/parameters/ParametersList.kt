package com.esms.views.parameters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import com.esms.models.Parameters


@Composable
fun ParametersList(params: Parameters) {
    val editableParams = params.persistentEditableParams()
    val scrollState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = scrollState
    ) {
        editableParams.forEach { param ->
            item {
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
                            text = param.name,
                            color = MaterialTheme.colors.onSurface
                        )
                    }


                    if (param.options.isEmpty()) {
                        // IconButton to edit/get more info
                        IconButton(
                            onClick = {
                                // TODO: get user input somehow
                                  param.setter("User input")
                            },
                            modifier = Modifier.size(48.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit ${param.name}",
                            )
                        }
                    } else {
                        Box{
                            var expanded by remember { mutableStateOf(false) }
                            var state by remember { mutableStateOf(param.currentState) }
                            Button(onClick = { expanded = true }) {
                                Text(state)
                            }
                            DropdownMenu(expanded = expanded, onDismissRequest = {expanded = false}) {
                                param.options.forEach { option ->
                                    DropdownMenuItem(onClick = {
                                        param.setter(option)
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
        }
    }
}