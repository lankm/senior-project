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

    secondary = Color.Gray,
    secondaryVariant = Color.DarkGray,

    background = Color.White,
    surface = Color.LightGray,
    error = Color.Red,

    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Gray,
    onSurface = Color.Black,
    onError = Color.Red,
)

private val LightColors = lightColors(
    primary = Color.Blue,
    primaryVariant = Color.Cyan,

    secondary = Color.Gray,
    secondaryVariant = Color.DarkGray,

    background = Color.White,
    surface = Color.LightGray,
    error = Color.Red,

    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Gray,
    onSurface = Color.Black,
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