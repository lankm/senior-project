package com.esms.services.engines.custom

import com.esms.services.engines.CryptographyEngine

/**
 * An implementation of the CryptographyEngine interface that performs a Caesar cipher encryption.
 * The Caesar cipher shifts each letter in the text by a fixed number of positions in the alphabet.
 *
 * @param parameters A string containing the numerical value for the shift.
 *                   If the string is not a valid integer, defaults to a shift of 0.
 */
class CaesarCipherEngine(parameters: String) : CryptographyEngine {
    // Holds the number of positions to shift the characters in the Caesar cipher
    private var shift: Int = parameters.toIntOrNull() ?: 0

    /**
     * Encrypts the provided text using the Caesar cipher.
     * Each letter in the text is shifted forward in the alphabet by the initialized shift amount.
     *
     * @param text The text to be encrypted.
     * @return The encrypted string.
     */
    override fun encrypt(text: String): String {
        return text.map { char ->
            shiftChar(char, shift)
        }.joinToString("")
    }

    /**
     * Decrypts the provided text which was encrypted using the Caesar cipher.
     * Each letter in the text is shifted backward in the alphabet by the initialized shift amount.
     *
     * @param encryptedText The text to be decrypted.
     * @return The decrypted string.
     */
    override fun decrypt(encryptedText: String): String {
        return encryptedText.map { char ->
            shiftChar(char, -shift)
        }.joinToString("")
    }

    /**
     * Helper function to shift a single character by the given number of positions.
     * Wraps around the alphabet and maintains the case of the letter.
     *
     * @param c The character to shift.
     * @param shift The number of positions to shift the character.
     * @return The shifted character.
     */
    private fun shiftChar(c: Char, shift: Int): Char {
        return if (c.isLetter()) {
            val base = if (c.isUpperCase()) 'A' else 'a'
            val offset = Math.floorMod(c - base + shift, 26)
            base + offset
        } else {
            c
        }
    }
}
