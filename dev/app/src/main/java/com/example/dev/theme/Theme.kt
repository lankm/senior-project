package com.example.dev.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColors = darkColors(
    primary = Color.Blue,
    primaryVariant = Color.Cyan,

    secondary = Color.Gray,
    secondaryVariant = Color.DarkGray,

    background = Color.White,
    surface = Color.Gray,
    error = Color.Red,

    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.Red,
)

private val LightColors = lightColors(
    primary = Color.Blue,
    primaryVariant = Color.Cyan,

    secondary = Color.Gray,
    secondaryVariant = Color.DarkGray,

    background = Color.White,
    surface = Color.Gray,
    error = Color.Red,

    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.White,
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