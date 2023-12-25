package com.esms.views.parameters

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.esms.models.Parameters

@Composable
fun ParametersScreen(navController: NavController, params: Parameters) {
    Scaffold (
        topBar = { ParametersTopBar(navController, params) },
        content = { innerPadding -> Box(modifier = Modifier.padding(innerPadding)) {
            ParametersList(params)
        }
        }
    )
}
