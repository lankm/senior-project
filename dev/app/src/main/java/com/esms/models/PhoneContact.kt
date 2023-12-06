package com.esms.models

import androidx.compose.ui.graphics.ImageBitmap

data class PhoneContact(
    val pfp: String?,
    val name: String,
    val number: String // TODO: change to long and format from there (Maybe not, they are treated like strings by the system in all contexts)
)

