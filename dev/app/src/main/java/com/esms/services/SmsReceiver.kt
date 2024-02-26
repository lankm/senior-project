package com.esms.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import com.esms.models.SMSMessage

class SmsReceiver(private val onSmsReceived: (SMSMessage) -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            val messages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
            val message = messages[0] ?: return
            val messageText = messages.joinToString(separator = "") {it.messageBody}
            onSmsReceived(SMSMessage(messageText, message.originatingAddress?:"", message.timestampMillis, false, SMSMessage.RECEIVED, 0))
        }
    }
}

