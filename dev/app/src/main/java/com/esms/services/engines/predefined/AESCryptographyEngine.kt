package com.esms.services.engines.predefined

import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import java.security.MessageDigest

class AESCryptographyEngine(keyString: String) : AbstractJavaxCryptographyEngine() {
    override val secretKey: SecretKey
    override val cipherAlgorithm = "AES/CBC/PKCS5Padding"
    override val ivSize = 16

    init {
        val keyBytes = MessageDigest.getInstance("SHA-256").digest(keyString.toByteArray())
        secretKey = SecretKeySpec(keyBytes, "AES")
    }
}
