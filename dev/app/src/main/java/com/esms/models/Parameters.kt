package com.esms.models

import android.content.Context
import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import com.esms.services.CryptographyEngineGenerator
import com.esms.services.SharedPreferencesService
import com.esms.services.engines.CryptographyEngine
import com.esms.services.engines.custom.PlainTextEngine
import com.esms.views.parameters.selectors.ColorSelector
import com.esms.views.parameters.selectors.FreeSelector
import com.esms.views.parameters.selectors.ModalSelector
import com.esms.views.parameters.selectors.OptionsSelector
import com.esms.views.parameters.selectors.SectionMarker
import java.lang.Long.parseLong

class Parameters (context: Context) : ViewModel(){
    // Constants
    val DEFAULT_ENCRYPTION_ALGORITHM = "AES"
    val DEFAULT_ENCRYPTION_PARAMETERS = "insecure"
    val DEFAULT_LABEL = "(Default)"

    // Services
    private val engineGen = CryptographyEngineGenerator()
    private val saveSystem = SharedPreferencesService(context)

    // Ephemeral Params
    var loaded = mutableStateOf(false)
    var currentContact = mutableStateOf<PhoneContact?>(null)
        private set

    val showSelectors = mutableStateOf(true)

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

    private var numberToNickname = mutableMapOf<String, String>()
    fun getNicknameFor(number: String, default: String) : String {
        return numberToNickname[number] ?: default
    }
    fun setNicknameFor(number: String, nickname: String) {
        numberToNickname[number] = nickname
    }

    private var numberToLastMessageTime = mutableMapOf<String, String>()
    fun getLastMessageTimeFor(number: String) : Long {
        return parseLong(numberToLastMessageTime[number] ?: "0")
    }
    fun setLastMessageTimeFor(number: String, timestamp: Long) {
        if(getLastMessageTimeFor(number) < timestamp){
            numberToLastMessageTime[number] = timestamp.toString()
            persist()
        }
    }

    var theme = mutableStateOf("System"); // Light, Dark, System, Custom

    private val customColors = mutableStateOf(
        darkColors(
            primary = Color(0xFF1111AA),
            primaryVariant = Color(0xFF116666),
            onPrimary = Color(0xFFEEEEEE),

            secondary = Color(0xFF771177),
            secondaryVariant = Color(0xFF804040),
            onSecondary = Color(0xFFEEEEEE),

            background = Color(0xFF333333),
            onBackground = Color(0xFFEEEEEE),

            surface = Color(0xFF111111),
            onSurface = Color(0xFFEEEEEE),

            error = Color(0xFFFF7070),
            onError = Color(0xFF111111),
        )
    )
    private var customColorsMap = getCustomColorsMap().toMutableMap()
    fun getCustomColors() : Colors {
        return customColors.value;
    }
    private fun setCustomColorsWithMap(stringMap: Map<String, String>) {
        if(stringMap.isEmpty())
            return
        customColors.value = darkColors(
            primary = Color(stringMap["primary"]!!.toInt()),
            primaryVariant = Color(stringMap["primaryVariant"]!!.toInt()),
            onPrimary = Color(stringMap["onPrimary"]!!.toInt()),

            secondary = Color(stringMap["secondary"]!!.toInt()),
            secondaryVariant = Color(stringMap["secondaryVariant"]!!.toInt()),
            onSecondary = Color(stringMap["onSecondary"]!!.toInt()),

            background = Color(stringMap["background"]!!.toInt()),
            onBackground = Color(stringMap["onBackground"]!!.toInt()),

            surface = Color(stringMap["surface"]!!.toInt()),
            onSurface = Color(stringMap["onSurface"]!!.toInt()),

            error = Color(stringMap["error"]!!.toInt()),
            onError = Color(stringMap["onError"]!!.toInt()),
        )
        customColorsMap = stringMap.toMutableMap()
    }
    private fun getCustomColorsMap() : Map<String, String>{
        return mapOf(
            "primary" to customColors.value.primary.toArgb().toString(),
            "primaryVariant" to customColors.value.primaryVariant.toArgb().toString(),
            "onPrimary" to customColors.value.onPrimary.toArgb().toString(),
            "secondary" to customColors.value.secondary.toArgb().toString(),
            "secondaryVariant" to customColors.value.secondaryVariant.toArgb().toString(),
            "onSecondary" to customColors.value.onSecondary.toArgb().toString(),
            "background" to customColors.value.background.toArgb().toString(),
            "onBackground" to customColors.value.onBackground.toArgb().toString(),
            "surface" to customColors.value.surface.toArgb().toString(),
            "onSurface" to customColors.value.onSurface.toArgb().toString(),
            "error" to customColors.value.error.toArgb().toString(),
            "onError" to customColors.value.onError.toArgb().toString(),
        )
    }

    // Persistence Functions
    val ENCRYPTION_ALGORITHMS = "0"
    val ENCRYPTION_PARAMETERS = "1"
    val SAVE_ENCRYPTION_PARAMETER = "2"
    val NICKNAMES = "3"
    val TIMESTAMPS = "4"
    val THEME = "5"
    val CUSTOM_THEME = "6"

    fun persist() {
        val maps = mapOf(
            ENCRYPTION_ALGORITHMS to numberToEncryptionAlgorithm.toMap(),
            ENCRYPTION_PARAMETERS to numberToEncryptionParameters.toMap(),
            SAVE_ENCRYPTION_PARAMETER to mapOf("" to saveEncryptionParameter.value),
            NICKNAMES to numberToNickname.toMap(),
            TIMESTAMPS to numberToLastMessageTime.toMap(),
            THEME to mapOf("" to theme.value),
            CUSTOM_THEME to getCustomColorsMap(),
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
            numberToNickname = maps[NICKNAMES]?.toMutableMap() ?: mutableMapOf()
            numberToLastMessageTime = maps[TIMESTAMPS]?.toMutableMap() ?: mutableMapOf()
            theme.value = maps.getOrDefault(THEME, mapOf("" to "System")).getOrDefault("", "System")
            setCustomColorsWithMap(maps.getOrDefault(CUSTOM_THEME, mapOf()))

            loaded.value = true
        } catch (_: Exception){}
    }

    // Editable Parameters
    fun persistentEditableParams(currentContact: PhoneContact?) : List<@Composable ()->Unit> {
        val globalParams = currentContact == null
        return listOfNotNull(
            SectionMarker("Contact Specific Settings", isNull = globalParams),
            nicknameSelector(currentContact),
            SectionMarker("Encryption Settings", isNull = globalParams),
            encryptionAlgorithmSelector(currentContact),
            encryptionParameterSelector(currentContact),
            SectionMarker("Default Encryption Settings"),
            defaultEncryptionAlgorithmSelector(),
            defaultEncryptionParameterSelector(),
            globalEncryptionKeySelector(),
            SectionMarker("Theme Settings"),
            primaryThemeSelector(),
            if(theme.value == "Custom") ModalSelector("Custom Theme Colors", customColorSelectors()) else null,
        )
    }

    private fun encryptionAlgorithmSelector(currentContact: PhoneContact?) : (@Composable ()->Unit)? {
        if(currentContact == null)
            return null
        return OptionsSelector(
            name = "Encryption Algorithm",
            setter = { algorithm: String -> run {
                if (algorithm.contains(DEFAULT_LABEL))
                    numberToEncryptionAlgorithm.remove(currentContact.number)
                else
                    numberToEncryptionAlgorithm[currentContact.number] = algorithm
                persist()
                setCurrentEncryptionEngine(currentContact.number)
            }},
            options = listOf("${getEncryptionAlgorithmFor("")} $DEFAULT_LABEL") +
                      CryptographyEngineGenerator().getRegisteredEngines(),
            currentState = getEncryptionAlgorithmFor(currentContact.number) +
                           defaultLabelIfDefault(currentContact)
        )
    }
    private fun encryptionParameterSelector(currentContact: PhoneContact?) : (@Composable ()->Unit)? {
        if(currentContact == null)
            return null
        return FreeSelector(
            name = "Encryption Parameter",
            setter = { algorithm: String -> run {
                numberToEncryptionParameters[currentContact.number] = algorithm
                persist()
                setCurrentEncryptionEngine(currentContact.number)
            }},
            currentState = getEncryptionParametersFor(currentContact.number)
        )
    }
    private fun defaultEncryptionAlgorithmSelector() : @Composable ()->Unit{
        return OptionsSelector(
            name = "Default Encryption Algorithm",
            setter = { algorithm: String -> run {
                numberToEncryptionAlgorithm[""] = algorithm
                persist()
                setCurrentEncryptionEngine("")
            }},
            options = CryptographyEngineGenerator().getRegisteredEngines(),
            currentState = getEncryptionAlgorithmFor("")
        )
    }
    private fun defaultEncryptionParameterSelector() : @Composable ()->Unit {
        return FreeSelector(
            name = "Default Encryption Parameter",
            setter = { algorithm: String -> run {
                numberToEncryptionParameters[""] = algorithm
                persist()
                setCurrentEncryptionEngine("")
            }},
            currentState = getEncryptionParametersFor("")
        )
    }
    private fun globalEncryptionKeySelector() : @Composable ()->Unit {
        return FreeSelector(
            name = "Save Encryption Key",
            setter = { key: String -> run {
                saveEncryptionParameter.value = key
                persist()
            }},
            currentState = saveEncryptionParameter.value,
            comment = " (\"$DEFAULT_ENCRYPTION_PARAMETERS\" = no auth screen)"
        )
    }
    private fun nicknameSelector(currentState: PhoneContact?) : (@Composable ()->Unit)? {
        if(currentState == null)
            return null

        return FreeSelector(
            name = "Nickname",
            setter = { key: String -> run {
                if(key.isNotBlank())
                    setNicknameFor(currentState.number, key)
                else
                    numberToNickname.remove(currentState.number)
                persist()
            }},
            currentState = getNicknameFor(currentState.number, currentState.name),
            comment = " (Leave this blank -> Reset to ${currentState.name})"
        )
    }
    private fun primaryThemeSelector() : @Composable ()->Unit {
        return OptionsSelector(
            name = "Color Theme",
            setter = { key: String -> run {
                theme.value = key
                persist()
            }},
            currentState = theme.value,
            options = listOf("System", "Dark", "Light", "Custom")
        )
    }
    private fun customColorSelectors() : Array<@Composable ()->Unit> {
        return listOf(
                "primary",
                "primaryVariant",
                "onPrimary",
                "secondary",
                "secondaryVariant",
                "onSecondary",
                "background",
                "onBackground",
                "surface",
                "onSurface",
                "error"
            ).map { predefinedColorSelector(it) }.toTypedArray()
    }
        private fun predefinedColorSelector(name: String) : @Composable () -> Unit {
        return ColorSelector(
            name,
            setter = {
                color: Color -> run {
                    customColorsMap[name] = color.toArgb().toString()
                    setCustomColorsWithMap(customColorsMap)
                    persist()
                }
            },
            currentState = Color(customColorsMap[name]!!.toInt())
        )
    }

    // Initialization
    init {
        load()
    }

    // Private Helper Functions
    private fun defaultLabelIfDefault(currentContact: PhoneContact): String {
        return if (!numberToEncryptionAlgorithm.containsKey(currentContact.number)) " $DEFAULT_LABEL" else ""
    }
}