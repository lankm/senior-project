package com.esms.views.parameters.selectors

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.toColorInt
import okhttp3.internal.toHexString

fun ColorSelector(
    name: String,
    setter: (Color)->Unit,
    currentState: MutableState<Color>,
): @Composable () -> Unit{
    return FreeSelector(
        name,
        setter = {
            try{
                setter(Color(it.toColorInt()))
                currentState.value = Color(it.toColorInt())
            } catch(_: Exception) {}
        },
        currentState = "#" + currentState.value.toArgb().toHexString().padStart(8, '0'),
        comment = "Format: #AARRGGBB where XX is 00-FF",
        tint = currentState
    )
}