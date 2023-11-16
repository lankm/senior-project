package com.esms.services.engines.predefined

import com.esms.services.engines.CryptographyEngine
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import java.security.MessageDigest
import java.util.Base64

class JavaxCryptographyEngine(keyString: String, private val algorithm: String) : CryptographyEngine {
    private val secretKey: SecretKey

    init {
        // Use a hash function to derive a key from the string
        val keyBytes = MessageDigest.getInstance("SHA-256").digest(keyString.toByteArray())
        secretKey = SecretKeySpec(keyBytes, algorithm)
    }

    override fun encrypt(text: String): String {
        val cipher = Cipher.getInstance("$algorithm/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val encryptedBytes = cipher.doFinal(text.toByteArray())
        return Base64.getEncoder().encodeToString(encryptedBytes)
    }

    override fun decrypt(encryptedText: String): String {
        val cipher = Cipher.getInstance("$algorithm/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, secretKey)
        val decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText))
        return String(decryptedBytes)
    }
}
