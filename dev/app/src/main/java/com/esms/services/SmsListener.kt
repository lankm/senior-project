package com.esms.services

import android.content.IntentFilter
import android.provider.Telephony
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.esms.models.SMSMessage


@Composable
fun SmsListener(onSmsReceived: (SMSMessage) -> Unit) {
    val context = LocalContext.current
    val smsReceiver = remember {
        SmsReceiver(onSmsReceived)
    }

    DisposableEffect(context) {
        context.registerReceiver(smsReceiver, IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION))

        onDispose {
            context.unregisterReceiver(smsReceiver)
        }
    }
}