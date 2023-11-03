package com.esms.views

import androidx.compose.runtime.Composable

@Composable
fun AuthScreen(onAuthGranted: () -> Unit) {
    onAuthGranted()
    //TODO login screen. should default to off on first run.
}