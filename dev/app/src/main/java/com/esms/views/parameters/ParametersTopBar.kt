package com.esms.views.parameters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.esms.models.Parameters
import com.esms.views.contacts.ContactBox


@Composable
fun ParametersTopBar(navController: NavController, params: Parameters) {
    val currentContact = remember {params.currentContact.value}
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .height(60.dp)
            .padding(5.dp)
    ) {
        // Left IconButton
        IconButton(
            onClick = {
                if(navController.previousBackStackEntry?.destination?.route == "contacts")
                    params.currentContact.value = null
                navController.popBackStack()
            },
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back to contacts",
                tint = MaterialTheme.colors.onSurface
            )
        }

        // Center Content
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 48.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            if(currentContact != null)
                Column(modifier = Modifier.fillMaxHeight()) {
                    ContactBox(contact = currentContact)
                    Text(
                        text = "Contact Parameters",
                        color = MaterialTheme.colors.onSurface
                    )
                }

            else
                Text(
                    text = "Global Parameters",
                    color = MaterialTheme.colors.onSurface
                )
        }
    }
}