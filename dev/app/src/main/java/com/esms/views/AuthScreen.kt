package com.esms.views

import android.widget.Toast
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.esms.models.LocalParameters
import kotlin.system.exitProcess

@Composable
fun AuthScreen(onAuthGranted: () -> Unit) {
    val params = LocalParameters.current
    val context = LocalContext.current
    if(params.loaded.value)
        onAuthGranted()
    else {
        var text by remember { mutableStateOf("")}
        AlertDialog(
            onDismissRequest = { exitProcess(0) },
            title = { Text(text = "Authenticate") },
            text = { TextField(
                value = "⬤".repeat(text.length),
                onValueChange = { text = it },
                label = { Text("Password") }
            )
            },
            confirmButton = {
                Button(
                    onClick = {
                        params.load(text)
                        if(params.loaded.value)
                            onAuthGranted()
                        else {
                            text = ""
                            Toast.makeText(context, "Incorrect Password", Toast.LENGTH_SHORT).show()
                        }

                    }
                ) { Text("Decrypt") }
            },
            dismissButton = {
                Button(
                    onClick = { exitProcess(0) },
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant)
                ) { Text("Exit", color = MaterialTheme.colors.onPrimary) }
            }
        )
    }
}