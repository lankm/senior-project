package com.esms.services.engines.custom

import com.esms.services.engines.CryptographyEngine

/**
 * @param parameters A string that is ignored, but included for consistency.
 */
class PlainTextEngine(parameters : String) : CryptographyEngine {
    /**
     * Returns the same text as provided. No encryption is performed.
     *
     * @param text The text to be 'encrypted'.
     * @return The same text as input.
     */
    override fun encrypt(text: String): String {
        return text
    }

    /**
     * Returns the same text as provided. No decryption is performed.
     *
     * @param encryptedText The text to be 'decrypted'.
     * @return The same text as input.
     */
    override fun decrypt(encryptedText: String): String {
        return encryptedText
    }
}
