package com.esms.models

data class Contact(
    val pfp: Int,   // TODO: refactor so this to come from a database instead of res/drawable
    val name: String,
    val number: String // TODO: change to long and format from there
)
