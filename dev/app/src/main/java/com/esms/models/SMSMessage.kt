package com.esms.models

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class SMSMessage(
    val body: String,
    val extAddr: String,
    var date: Long,
    val read: Boolean,
    val type: Int,
    val thread: Int,
    // val service: String // null for some reason
) {
    init {
        date -= type*1000
    }
    companion object {
        const val SENT = 0
        const val RECEIVED = 1}
}

fun Long.parseDate(): String {
    val date = Date(this)
    val formatter = SimpleDateFormat("dd/MMM/yyyy h:mm\na", Locale.getDefault())
    return formatter.format(date)
}