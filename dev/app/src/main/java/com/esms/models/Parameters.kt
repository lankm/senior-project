package com.esms.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.esms.services.CryptographyEngineGenerator
import com.esms.services.engines.CryptographyEngine
import com.esms.services.engines.custom.PlainTextEngine
import java.util.concurrent.Callable

class Parameters : ViewModel(){
    // Ephemeral Params
    var currentContact = mutableStateOf<PhoneContact?>(null)
        private set
    fun setCurrentContact(contact: PhoneContact?) {
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
    fun persistentEditableParams() : List<Pair<String, (String) -> Unit>> {
        return listOf(
            Pair("Default Encryption Algorithm (Current : ${defaultEncryptionAlgorithm.value})",  { algorithm: String -> defaultEncryptionAlgorithm.value = algorithm })
        )
    }

    var defaultEncryptionAlgorithm = mutableStateOf("AES")
        private set
}