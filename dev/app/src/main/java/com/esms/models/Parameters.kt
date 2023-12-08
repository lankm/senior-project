package com.esms.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.esms.services.CryptographyEngineGenerator
import com.esms.services.engines.CryptographyEngine
import com.esms.services.engines.custom.PlainTextEngine
import com.esms.views.contacts.sampleContact

class Parameters (contact: PhoneContact = sampleContact) : ViewModel(){
    // Ephemeral Params
    var currentContact = mutableStateOf(contact)
        private set
    fun setCurrentContact(contact: PhoneContact) {
        currentContact.value = contact
    }

    var currentEncryptionEngine = mutableStateOf<CryptographyEngine>(PlainTextEngine(""))
        private set
    fun setCurrentEncryptionEngine(name: String, parameters: String){
        currentEncryptionEngine.value = CryptographyEngineGenerator().createEngine(name, parameters)
    }

    var currentMessageAdder = mutableStateOf<((SMSMessage) -> Unit)?>(null)
        private set
    fun setCurrentMessageAdder(func: (SMSMessage)->Unit){
        currentMessageAdder.value = func
    }
    fun runCurrentMessageAdder(msg: SMSMessage){
        currentMessageAdder.value?.invoke(msg)
    }

    // Saved Params
}