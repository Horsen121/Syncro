package com.example.syncro.utils

import android.content.Context
import com.google.crypto.tink.Aead
import com.google.crypto.tink.KeyTemplates
import com.google.crypto.tink.integration.android.AndroidKeysetManager

class CryptoManager(context: Context) {
    private val aead: Aead by lazy {
        AndroidKeysetManager.Builder()
            .withSharedPref(context, "master_keyset", "master_key_preference")
            .withKeyTemplate(KeyTemplates.get("AES256_GCM"))
            .withMasterKeyUri("android-keystore://master_key")
            .build()
            .keysetHandle
            .getPrimitive(Aead::class.java)
    }

    fun encrypt(plaintext: String): String {
        val bytes = aead.encrypt(plaintext.toByteArray(), null)
        return android.util.Base64.encodeToString(bytes, android.util.Base64.DEFAULT)
    }

    fun decrypt(ciphertext: String): String {
        val bytes = android.util.Base64.decode(ciphertext, android.util.Base64.DEFAULT)
        return String(aead.decrypt(bytes, null))
    }
}
