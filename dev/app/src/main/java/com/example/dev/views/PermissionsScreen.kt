package com.example.dev.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

// If more permisisons are needed, make one chain to the next until all permissions are granted.
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionsScreen(onPermissionGranted: () -> Unit) {
    val readSmsPermission = rememberPermissionState(
        android.Manifest.permission.READ_SMS
    )
    if (readSmsPermission.status.isGranted) {
        onPermissionGranted()
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val textToShow = if (readSmsPermission.status.shouldShowRationale) {
                "Permission is important for this app. Please grant the permission."
            } else {
                "Permission required for this application to work. Please grant the permission."
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                text = textToShow,
                textAlign = TextAlign.Center
            )
            Button(onClick = {readSmsPermission.launchPermissionRequest()}) {
                Text("Request Permission")
            }
        }
    }
}