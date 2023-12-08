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

    private var numberToEncryptionAlgorithm = mutableMapOf<String, String>()
    fun getEncryptionAlgorithmFor(number: String) : String{
        return numberToEncryptionAlgorithm[number] ?: defaultEncryptionAlgorithm.value
    }

    private var numberToEncryptionParameters = mutableMapOf<String, String>()
    fun getEncryptionParametersFor(number: String) : String{
        return numberToEncryptionParameters[number] ?: defaultEncryptionParameters.value
    }

    // Editable Parameters
    fun persistentEditableParams() : List<EditableParamater> {
        return listOf(
            EditableParamater(
                "Default Encryption Algorithm",
                { algorithm: String -> defaultEncryptionAlgorithm.value = algorithm },
                CryptographyEngineGenerator().getRegisteredEngines(),
                defaultEncryptionAlgorithm.value
            ),
        )
    }

    var defaultEncryptionAlgorithm = mutableStateOf("AES")
        private set
    var defaultEncryptionParameters = mutableStateOf("AES")
        private set
}

data class EditableParamater(val name: String, val setter: (String)->Unit, val options: List<String>, val currentState: String)