package com.esms.services.engines.predefined

import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import java.security.MessageDigest
import javax.crypto.spec.DESKeySpec

class DESCryptographyEngine(keyString: String) : AbstractJavaxCryptographyEngine() {
    override val secretKey: SecretKey
    override val cipherAlgorithm = "DES/CBC/PKCS5Padding"
    override val ivSize = 8

    init {
        val keyBytes = MessageDigest.getInstance("SHA-256").digest(keyString.toByteArray())
        secretKey = SecretKeySpec(DESKeySpec(keyBytes).key, "DES")
    }
}
