package com.esms.views.parameters

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun ParametersScreen(navController: NavController) {
    Scaffold (
        topBar = { ParametersTopBar(navController) },
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                ParametersList()
            }
        }
    )
}
