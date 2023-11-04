package com.esms.views.contacts

import androidx.compose.foundation.Image
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
import com.esms.models.PhoneContact

@Composable
fun ContactBox(contact: PhoneContact) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(50.dp)
    ) {
        // pfp
        Image(
            painter = painterResource(id = contact.pfp),
            contentDescription = "My Image",
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
    ContactBox(sampleContact)
}
//TODO remove this
val sampleContact = PhoneContact(
    pfp = R.drawable.ic_launcher_background,
    name = "Landon Moon",
    number = "817-694-6767"
)
