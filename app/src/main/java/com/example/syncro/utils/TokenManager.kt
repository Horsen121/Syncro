package com.example.syncro.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first

class TokenManager(
    private val dataStore: DataStore<Preferences>,
    private val cryptoManager: CryptoManager
) {
    companion object {
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
    }

    suspend fun saveToken(accessToken: String, refreshToken: String) {
        val encryptedAccessToken = cryptoManager.encrypt(accessToken)
        val encryptedRefreshToken = cryptoManager.encrypt(refreshToken)
        dataStore.edit { prefs ->
            prefs[ACCESS_TOKEN_KEY] = encryptedAccessToken
            prefs[REFRESH_TOKEN_KEY] = encryptedRefreshToken
        }
    }

    suspend fun getAccessToken(): String? {
        val prefs = dataStore.data.first()
        val encryptedToken = prefs[ACCESS_TOKEN_KEY] ?: return null
        return try {
            cryptoManager.decrypt(encryptedToken)
        } catch (e: Exception) {
            null
        }
    }
    suspend fun getRefreshToken(): String? {
        val prefs = dataStore.data.first()
        val encryptedToken = prefs[REFRESH_TOKEN_KEY] ?: return null
        return try {
            cryptoManager.decrypt(encryptedToken)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun clear() {
        dataStore.edit { it.clear() }
    }
}

