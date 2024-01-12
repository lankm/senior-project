package com.esms.views.parameters

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.esms.models.Parameters

@Composable
fun ParametersList(params: Parameters) {
    val currentContact = remember {params.currentContact.value}
    val editableParams = params.persistentEditableParams(currentContact)
    val scrollState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = scrollState
    ) {
        editableParams.forEach { param ->
            item {
                param()
            }
        }
    }
}