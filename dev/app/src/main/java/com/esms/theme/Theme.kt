package com.esms.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// TODO: Do a big theme refactor
private val DarkColors = darkColors(
    primary = Color.Blue,
    primaryVariant = Color.Cyan,
    onPrimary = Color.Black,

    secondary = Color.Magenta,
    secondaryVariant = Color.DarkGray,
    onSecondary = Color.Black,

    background = Color.DarkGray,
    onBackground = Color.White,

    surface = Color.Black,
    onSurface = Color.White,

    error = Color.Red,
    onError = Color.Red,
)

private val LightColors = lightColors(
    primary = Color.Blue,
    primaryVariant = Color.Cyan,
    onPrimary = Color.White,

    secondary = Color.Gray,
    secondaryVariant = Color.DarkGray,
    onSecondary = Color.Black,

    background = Color.White,
    onBackground = Color.Gray,

    surface = Color.LightGray,
    onSurface = Color.Black,

    error = Color.Red,
    onError = Color.Red,
)

@Composable
fun EsmsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkColors else LightColors,
        typography = Typography,
        content = content
    )
}