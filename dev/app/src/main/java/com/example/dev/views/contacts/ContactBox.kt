package com.example.dev.views.contacts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dev.R
import com.example.dev.models.Contact

@Composable
fun ContactBox(contact: Contact) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(5.dp)
    ) {
        // pfp
        Image(
            painter = painterResource(id = contact.pfp),
            contentDescription = "My Image",
            modifier = Modifier
                .clip(CircleShape)
                .size(50.dp)
        )
        
        Spacer(modifier = Modifier.padding(horizontal = 5.dp))
        
        Column() {
            // Name
            Text(
                text = contact.name,
                color = MaterialTheme.colors.onSurface
            )
            // Number
            Text(
                text = contact.number,
                color = MaterialTheme.colors.onSurface
            )
        }

    }
}



@Preview
@Composable
fun ContactBoxPreview() {
    ContactBox(sample_contacts[0])
}