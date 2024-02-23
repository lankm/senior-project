package com.esms.views.contacts.pic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.esms.R

@Composable
fun ProfilePicture(name: String, number: Int, url: String? = null) {
    if (url != null) {
        val fallbackPainter = painterResource(id = R.drawable.ic_launcher_background)
        AsyncImage(
            model = url,
            placeholder = fallbackPainter,
            error = fallbackPainter,
            fallback = fallbackPainter,
            contentDescription = name,
            modifier = Modifier
                .clip(CircleShape)
                .fillMaxHeight()
        )
    } else {
        val colorFromNumber = Color((number and 0xFFFFFF).toLong() or 0xFF000000)
        Box (
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(
                    shape = RoundedCornerShape(100),
                    color = colorFromNumber
                )
                .fillMaxHeight()
                .aspectRatio(1F)
        ) {
            Text (
                text = name.first().toString(),
                fontSize = 30.sp,
                color = if (colorFromNumber.luminance() < 0.6) Color.White else Color.Black
            )
        }
    }
}