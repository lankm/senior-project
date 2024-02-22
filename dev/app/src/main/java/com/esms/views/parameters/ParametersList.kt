package com.esms.views.parameters

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.esms.models.LocalParameters

@Composable
fun ParametersList() {
    val params = LocalParameters.current
    val editableParams = params.getListOfEditableParameterSelectorsAndMarkers()
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