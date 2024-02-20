package com.esms.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.esms.models.LocalParameters

private val darkColors = darkColors(
    primary = Color(0xFF1111AA),
    primaryVariant = Color(0xFF116666),
    onPrimary = Color(0xFFEEEEEE),

    secondary = Color(0xFF771177),
    secondaryVariant = Color(0xFF804040),
    onSecondary = Color(0xFFEEEEEE),

    background = Color(0xFF333333),
    onBackground = Color(0xFFEEEEEE),

    surface = Color(0xFF111111),
    onSurface = Color(0xFFEEEEEE),

    error = Color(0xFFFF7070),
    onError = Color(0xFF111111),
)

private val lightColors = lightColors(
    primary = Color(0xFF1111FF),
    primaryVariant = Color(0xFF11FFFF),
    onPrimary = Color(0xFFFFFFFF),

    secondary = Color(0xFF888888),
    secondaryVariant = Color(0xFF444444),
    onSecondary = Color(0xFF111111),

    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF888888),

    surface = Color(0xFFCCCCCC),
    onSurface = Color(0xFF111111),

    error = Color(0xFFFF7070),
    onError = Color(0xFF111111),
)

@Composable
fun EsmsTheme(
    content: @Composable () -> Unit
) {
    val params = LocalParameters.current
    val theme = if (params.theme.value == "System")
                    (if (isSystemInDarkTheme()) "Dark" else "Light")
                else params.theme.value
    val context = LocalContext.current
    val activity = context as? Activity

    MaterialTheme(
        colors = when (theme) {
            "Custom" -> {
                activity?.window?.statusBarColor = params.getCustomColors().surface.toArgb()
                params.getCustomColors() }
            "Light" -> {
                activity?.window?.statusBarColor = lightColors.surface.toArgb()
                lightColors }
            else -> {
                activity?.window?.statusBarColor = darkColors.surface.toArgb()
                darkColors }
        },
        typography = Typography,
        content = content
    )
}