package com.esms.views.contacts

import coil.compose.AsyncImage
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.esms.R
import com.esms.models.Parameters
import com.esms.models.PhoneContact

@Composable
fun ContactBox(contact: PhoneContact, params: Parameters) {
    val fallbackPainter = painterResource(id = R.drawable.ic_launcher_background)
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(50.dp)
    ) {
        // pfp
        AsyncImage(
            model = contact.pfp,
            placeholder = fallbackPainter,
            error = fallbackPainter,
            fallback = fallbackPainter,
            contentDescription = params.getNicknameFor(contact.number, contact.name),
            modifier = Modifier
                .clip(CircleShape)
                .fillMaxHeight()
        )
        
        Spacer(modifier = Modifier.padding(horizontal = 5.dp))
        
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight()
        ) {
            // Name
            Text(
                text = params.getNicknameFor(contact.number, contact.name),
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



//TODO remove this
val sampleContact = PhoneContact(
    pfp = null,
    name = "Dohn Daycom",
    number = "555-888-9999"
)
