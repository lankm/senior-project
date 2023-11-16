package com.esms.services

import com.esms.services.engines.CryptographyEngine
import com.esms.services.engines.custom.*
import com.esms.services.engines.predefined.*

class CryptographyEngineGenerator {
    private val engines = mutableMapOf<String, (String) -> CryptographyEngine>()

    init {
        // Register engine types here
        registerEngine("PlainText") { params -> PlainTextEngine(params) }
        registerEngine("CaesarCipher") { params -> CaesarCipherEngine(params) }
        registerEngine("AES") { params -> JavaxCryptographyEngine(params, "AES") }
        registerEngine("DES") { params -> JavaxCryptographyEngine(params, "DES") }
        registerEngine("DESede") { params -> JavaxCryptographyEngine(params, "DESede") }
    }

    private fun registerEngine(name: String, creator: (String) -> CryptographyEngine) {
        engines[name] = creator
    }

    fun createEngine(name: String, parameters: String): CryptographyEngine? {
        val creator = engines[name]
        return creator?.invoke(parameters)
    }
}