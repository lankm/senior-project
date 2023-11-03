package com.example.dev.models

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class SMSMessage(
    val body: String,
    val sender: String,
    val date: Long,
    val read: Boolean,
    val type: Int,
    val thread: Int,
    // val service: String // null for some reason
)

fun Long.parseDate(): String {
    val date = Date(this)
    val formatter = SimpleDateFormat("dd/MMM/yyyy h:mm\na", Locale.getDefault())
    return formatter.format(date)
}