package com.esms.models

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.esms.services.CryptographyEngineGenerator
import com.esms.services.SharedPreferencesService
import com.esms.services.engines.CryptographyEngine
import com.esms.services.engines.custom.PlainTextEngine

class Parameters (context: Context) : ViewModel(){
    // Constants
    val DEFAULT_ENCRYPTION_ALGORITHM = "AES"
    val DEFAULT_ENCRYPTION_PARAMETERS = "insecure"

    // Services
    val engineGen = CryptographyEngineGenerator()
    val saveSystem = SharedPreferencesService(context)

    // Ephemeral Params
    var currentContact = mutableStateOf<PhoneContact?>(null)
        private set

    var currentEncryptionEngine = mutableStateOf<CryptographyEngine>(PlainTextEngine(""))
        private set
    fun setCurrentEncryptionEngine(number: String){
        currentEncryptionEngine.value = engineGen.createEngine(getEncryptionAlgorithmFor(number), getEncryptionParametersFor(number))
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
    fun getEncryptionAlgorithmFor(number: String?) : String{
        return numberToEncryptionAlgorithm[number] ?: numberToEncryptionAlgorithm[""] ?: DEFAULT_ENCRYPTION_ALGORITHM
    }

    private var numberToEncryptionParameters = mutableMapOf<String, String>()
    fun getEncryptionParametersFor(number: String?) : String {
        return numberToEncryptionParameters[number] ?: numberToEncryptionParameters[""] ?: DEFAULT_ENCRYPTION_PARAMETERS
    }

    // Persistence Functions
    val ENCRYPTION_ALGORITHMS = "0"
    val ENCRYPTION_PARAMETERS = "1"
    fun persist() {
        val maps = mapOf(
            ENCRYPTION_ALGORITHMS to numberToEncryptionAlgorithm.toMap(),
            ENCRYPTION_PARAMETERS to numberToEncryptionParameters.toMap(),
        )
        saveSystem.write(saveSystem.stringifyMapMap(maps))
    }
    fun load() {
        val maps = saveSystem.destringifyMaps(saveSystem.read())
        numberToEncryptionAlgorithm = maps[ENCRYPTION_ALGORITHMS]?.toMutableMap() ?: mutableMapOf("" to DEFAULT_ENCRYPTION_ALGORITHM)
        numberToEncryptionParameters = maps[ENCRYPTION_PARAMETERS]?.toMutableMap() ?: mutableMapOf("" to DEFAULT_ENCRYPTION_PARAMETERS)
    }

    // Editable Parameters
    fun persistentEditableParams() : List<EditableParameter> {
        return listOf(
            EditableParameter(
                name = "${defaultAlterationString()}Encryption Algorithm",
                setter = { algorithm: String -> run {
                    numberToEncryptionAlgorithm[currentContact.value?.number ?: ""] = algorithm
                    persist()
                    setCurrentEncryptionEngine(currentContact.value?.number ?: "")
                }},
                options = CryptographyEngineGenerator().getRegisteredEngines(),
                currentState = getEncryptionAlgorithmFor(currentContact.value?.number ?: "")
            ),
            EditableParameter(
                name = "${defaultAlterationString()}Encryption Parameter",
                setter = { algorithm: String -> run {
                    numberToEncryptionParameters[currentContact.value?.number ?: ""] = algorithm
                    persist()
                    setCurrentEncryptionEngine(currentContact.value?.number ?: "")
                }},
                options = listOf(),
                currentState = getEncryptionParametersFor(currentContact.value?.number ?: "")
            ),
        )
    }

    // Initialization
    init {
        load()
    }

    // Private Helper Functions
    private fun defaultAlterationString(): String {
        return if (currentContact.value == null) "Default " else ""
    }
}

data class EditableParameter(
    val name: String,
    val setter: (String)->Unit,
    val options: List<String>,
    var currentState: String
)