package com.esms.views

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
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberMultiplePermissionsState

// make sure to include these permission in AndroidManifest.xml as well
val permissions = listOf(
    android.Manifest.permission.READ_SMS,
    android.Manifest.permission.SEND_SMS,
    android.Manifest.permission.READ_CONTACTS
)

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionsScreen(onPermissionGranted: () -> Unit) {
    val permissionStates = rememberMultiplePermissionsState(permissions)

    if(permissionStates.allPermissionsGranted) {
        onPermissionGranted()
    } else {
        askPermission(permissionStates.revokedPermissions.first())
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AskPermission(permissionState: PermissionState) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // original format: android.permission.READ_SMS
        val permissionName = permissionState.permission.split(".").last().lowercase().replace('_',' ')
        val textToShow = "This application needs the '$permissionName' permission to function properly."

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            text = textToShow,
            textAlign = TextAlign.Center
        )
        Button(onClick = {permissionState.launchPermissionRequest()}) {
            Text("Request Permission")
        }
    }
}
