package com.qualape.api.utils

import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.SecretKeySpec

object Cryptographer {

    private const val ALGORITHM_NAME = "Blowfish"
    private const val CHARSET_NAME = "UTF-8"
    private val key: ByteArray = "developer".toByteArray()
    private val cipher: Cipher by lazy { Cipher.getInstance(ALGORITHM_NAME) }

    @Throws(
        NoSuchAlgorithmException::class,
        NoSuchPaddingException::class,
        InvalidKeyException::class,
        IllegalBlockSizeException::class,
        BadPaddingException::class,
        UnsupportedEncodingException::class
    )
    fun String.cryptographed(): String {
        val keySpec = SecretKeySpec(key, ALGORITHM_NAME)
        cipher.init(Cipher.ENCRYPT_MODE, keySpec)
        return Base64.getEncoder().encodeToString(
            cipher.
        doFinal(this.toByteArray(charset(CHARSET_NAME))))
    }

    @Throws(
        NoSuchAlgorithmException::class,
        NoSuchPaddingException::class,
        InvalidKeyException::class,
        IllegalBlockSizeException::class,
        BadPaddingException::class
    )
    fun String.decryptographed(): String {
        val keySpec = SecretKeySpec(key, ALGORITHM_NAME)
        val bytes = Base64.getDecoder().decode(this)
        cipher.init(Cipher.DECRYPT_MODE, keySpec)
        val decrypted = cipher.doFinal(bytes)
        return String(decrypted, Charset.forName(CHARSET_NAME))
    }
}