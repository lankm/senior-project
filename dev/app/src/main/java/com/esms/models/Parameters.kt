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
    private val engineGen = CryptographyEngineGenerator()
    private val saveSystem = SharedPreferencesService(context)

    // Ephemeral Params
    var loaded = mutableStateOf(false)
    var currentContact = mutableStateOf<PhoneContact?>(null)
        private set

    var currentEncryptionEngine = mutableStateOf<CryptographyEngine>(PlainTextEngine(""))
        private set
    fun setCurrentEncryptionEngine(number: String){
        currentEncryptionEngine.value = engineGen.createEngine(getEncryptionAlgorithmFor(number), getEncryptionParametersFor(number))
    }

    private var currentMessageAdder = mutableStateOf<((SMSMessage) -> Unit)?>(null)
    fun setCurrentMessageAdder(func: (SMSMessage)->Unit){
        currentMessageAdder.value = func
    }
    fun runCurrentMessageAdder(msg: SMSMessage){
        currentMessageAdder.value?.invoke(msg)
    }

    // Saved Params
    private var numberToEncryptionAlgorithm = mutableMapOf<String, String>()
    private fun getEncryptionAlgorithmFor(number: String?) : String{
        return numberToEncryptionAlgorithm[number] ?: numberToEncryptionAlgorithm[""] ?: DEFAULT_ENCRYPTION_ALGORITHM
    }

    private var numberToEncryptionParameters = mutableMapOf<String, String>()
    private fun getEncryptionParametersFor(number: String?) : String {
        return numberToEncryptionParameters[number] ?: numberToEncryptionParameters[""] ?: DEFAULT_ENCRYPTION_PARAMETERS
    }

    private var saveEncryptionParameter = mutableStateOf(DEFAULT_ENCRYPTION_PARAMETERS)

    // Persistence Functions
    val ENCRYPTION_ALGORITHMS = "0"
    val ENCRYPTION_PARAMETERS = "1"
    val SAVE_ENCRYPTION_PARAMETER = "2"
    fun persist() {
        val maps = mapOf(
            ENCRYPTION_ALGORITHMS to numberToEncryptionAlgorithm.toMap(),
            ENCRYPTION_PARAMETERS to numberToEncryptionParameters.toMap(),
            SAVE_ENCRYPTION_PARAMETER to mapOf("" to saveEncryptionParameter.value)
        )
        val saveEncryptor = engineGen.createEngine("AES", saveEncryptionParameter.value)
        val saveString = saveSystem.stringifyMapMap(maps)
        val encryptedSaveString = saveEncryptor.encrypt(saveString)
        saveSystem.write(encryptedSaveString)
    }
    fun load(key: String = DEFAULT_ENCRYPTION_PARAMETERS) {
        val savedString = saveSystem.read()
        val decryptingEngine = engineGen.createEngine("AES", key)
        val decryptedString = try {decryptingEngine.decrypt(savedString)} catch (_: Exception) {savedString}
        try {
            if(decryptedString == savedString && savedString != "")
                throw Exception()
            val maps = saveSystem.destringifyMaps(decryptedString)
            numberToEncryptionAlgorithm = maps[ENCRYPTION_ALGORITHMS]?.toMutableMap() ?: mutableMapOf("" to DEFAULT_ENCRYPTION_ALGORITHM)
            numberToEncryptionParameters = maps[ENCRYPTION_PARAMETERS]?.toMutableMap() ?: mutableMapOf("" to DEFAULT_ENCRYPTION_PARAMETERS)
            saveEncryptionParameter.value = maps.getOrDefault(SAVE_ENCRYPTION_PARAMETER, mapOf("" to DEFAULT_ENCRYPTION_PARAMETERS)).getOrDefault("", DEFAULT_ENCRYPTION_PARAMETERS)
            loaded.value = true
        } catch (_: Exception){}
    }

    // Editable Parameters
    fun persistentEditableParams(currentContact: PhoneContact?) : List<EditableParameter> {
        return listOf(
            EditableParameter(
                name = "${defaultAlterationString(currentContact)}Encryption Algorithm",
                setter = { algorithm: String -> run {
                    numberToEncryptionAlgorithm[currentContact?.number ?: ""] = algorithm
                    persist()
                    setCurrentEncryptionEngine(currentContact?.number ?: "")
                }},
                options = CryptographyEngineGenerator().getRegisteredEngines(),
                currentState = getEncryptionAlgorithmFor(currentContact?.number ?: "")
            ),
            EditableParameter(
                name = "${defaultAlterationString(currentContact)}Encryption Parameter",
                setter = { algorithm: String -> run {
                    numberToEncryptionParameters[currentContact?.number ?: ""] = algorithm
                    persist()
                    setCurrentEncryptionEngine(currentContact?.number ?: "")
                }},
                options = listOf(),
                currentState = getEncryptionParametersFor(currentContact?.number ?: "")
            ),
            EditableParameter(
                name = "Save Encryption Key",
                setter = { key: String -> run {
                    saveEncryptionParameter.value = key
                    persist()
                }},
                options = listOf(),
                currentState = saveEncryptionParameter.value,
                comment = " (\"$DEFAULT_ENCRYPTION_PARAMETERS\" = no auth screen)"
            ),
        )
    }

    // Initialization
    init {
        load()
    }

    // Private Helper Functions
    private fun defaultAlterationString(currentContact: PhoneContact?): String {
        return if (currentContact == null) "Default " else ""
    }
}

data class EditableParameter(
    val name: String,
    val setter: (String)->Unit,
    val options: List<String>,
    var currentState: String,
    val comment: String = "",
)