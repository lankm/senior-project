package com.example.dev.services

import android.content.Context
import android.net.Uri
import com.example.dev.models.SMSMessage

public fun readMessages(context: Context, type: String): List<SMSMessage> {
    val messages = mutableListOf<SMSMessage>()
    val cursor = context.contentResolver.query(
        Uri.parse("content://sms/$type"),
        null,
        null,
        null,
        null
    )
    cursor?.use {
        val indexBody = it.getColumnIndex("body")
        val indexSender = it.getColumnIndex("address")
        val indexDate = it.getColumnIndex("date")
        val indexRead = it.getColumnIndex("read")
        val indexType = it.getColumnIndex("type")
        val indexThread = it.getColumnIndex("thread_id")
        // val indexService = it.getColumnIndex("service_center")

        while (it.moveToNext()) {
            messages.add(
                SMSMessage(
                    body = it.getString(indexBody),
                    sender = it.getString(indexSender),
                    date = it.getLong(indexDate),
                    read = it.getString(indexRead).toBoolean(),
                    type = it.getInt(indexType),
                    thread = it.getInt(indexThread),
                    // service = it.getString(indexService)
                )
            )
        }
    }

    return messages
}