package com.esms.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.esms.views.contacts.sampleContact

class Parameters (contact: PhoneContact = sampleContact) : ViewModel(){
    var currentContact= mutableStateOf(contact)
        private set
    var currentName= mutableStateOf(contact.name)
        private set
    var currentAddress= mutableStateOf(contact.number)
        private set
    var currentPFP= mutableStateOf(contact.pfp)
        private set

    fun setCurrentContact(contact: PhoneContact) {
        currentContact.value = contact
        currentName.value = contact.name
        currentAddress.value = contact.number
        currentPFP.value = contact.pfp
    }

}