package com.esms.services.engines.predefined

import com.esms.services.engines.CryptographyEngine
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import java.security.SecureRandom
import java.util.Base64

abstract class AbstractJavaxCryptographyEngine : CryptographyEngine {
    protected abstract val secretKey: SecretKey
    protected abstract val cipherAlgorithm: String
    protected abstract val ivSize: Int

    private fun generateIv(): ByteArray = ByteArray(ivSize).apply { SecureRandom().nextBytes(this) }

    override fun encrypt(text: String): String {
        val cipher = Cipher.getInstance(cipherAlgorithm)
        val iv = generateIv()
        val ivSpec = IvParameterSpec(iv)

        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec)
        val encryptedBytes = cipher.doFinal(text.toByteArray())

        val combined = iv + encryptedBytes
        return Base64.getEncoder().encodeToString(combined)
    }

    override fun decrypt(encryptedText: String): String {
        val combined = Base64.getDecoder().decode(encryptedText)

        val iv = combined.copyOfRange(0, ivSize)
        val encryptedBytes = combined.copyOfRange(ivSize, combined.size)

        val ivSpec = IvParameterSpec(iv)
        val cipher = Cipher.getInstance(cipherAlgorithm)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec)
        val decryptedBytes = cipher.doFinal(encryptedBytes)

        return String(decryptedBytes)
    }
}