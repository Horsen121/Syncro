package com.example.utils

import android.content.Context
import android.util.Base64
import com.google.crypto.tink.Aead
import com.google.crypto.tink.KeyTemplates
import com.google.crypto.tink.RegistryConfiguration
import com.google.crypto.tink.integration.android.AndroidKeysetManager

class CryptoManager(context: Context) {
    private val aead: Aead by lazy {
        AndroidKeysetManager.Builder()
            .withSharedPref(context, "master_keyset", "master_key_preference")
            .withKeyTemplate(KeyTemplates.get("AES256_GCM"))
            .withMasterKeyUri("android-keystore://master_key")
            .build()
            .keysetHandle
            .getPrimitive(RegistryConfiguration.get(), Aead::class.java)
    }

    fun encrypt(plaintext: String): String {
        val bytes = aead.encrypt(plaintext.toByteArray(), null)
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }

    fun decrypt(ciphertext: String): String {
        val bytes = Base64.decode(ciphertext, Base64.DEFAULT)
        return String(aead.decrypt(bytes, null))
    }
}
