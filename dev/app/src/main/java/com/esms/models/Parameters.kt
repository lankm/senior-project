package com.esms.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.esms.services.CryptographyEngineGenerator
import com.esms.services.engines.CryptographyEngine
import com.esms.services.engines.custom.PlainTextEngine
import com.esms.views.contacts.sampleContact

class Parameters (contact: PhoneContact = sampleContact) : ViewModel(){
    var currentContact = mutableStateOf(contact)
        private set
    var currentName = mutableStateOf(contact.name)
        private set
    var currentAddress = mutableStateOf(contact.number)
        private set
    var currentPFP = mutableStateOf(contact.pfp)
        private set
    var currentEncryptionEngine = mutableStateOf<CryptographyEngine>(PlainTextEngine(""))
        private set

    fun setCurrentContact(contact: PhoneContact) {
        currentContact.value = contact
        currentName.value = contact.name
        currentAddress.value = contact.number
        currentPFP.value = contact.pfp
    }

    fun setCurrentEncryptionEngine(name: String, parameters: String){
        currentEncryptionEngine.value = CryptographyEngineGenerator().createEngine(name, parameters)
    }
}