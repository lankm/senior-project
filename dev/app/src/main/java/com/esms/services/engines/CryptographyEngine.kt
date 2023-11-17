package com.esms.services.engines

interface CryptographyEngine {
    /**
     * Encrypts the provided text.
     *
     * @param text The text to be encrypted.
     * @return The encrypted string.
     */
    fun encrypt(text: String): String

    /**
     * Decrypts the provided text.
     *
     * @param encryptedText The text to be decrypted.
     * @return The decrypted string.
     */
    fun decrypt(encryptedText: String): String
}