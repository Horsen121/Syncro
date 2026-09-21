package com.example.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentUserRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : CurrentUserRepository {

    private val scope = CoroutineScope(Dispatchers.IO)

    private object PreferencesKeys {
        val USER_ID = longPreferencesKey("user_id")
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_EMAIL = stringPreferencesKey("user_email")
    }

    override val userStream: Flow<CurrentUser?> = dataStore.data.map { preferences ->
        val id = preferences[PreferencesKeys.USER_ID]
        val name = preferences[PreferencesKeys.USER_NAME]
        val email = preferences[PreferencesKeys.USER_EMAIL]

        if (id != null && name != null && email != null) {
            CurrentUser(id = id, name = name, email = email)
        } else {
            null
        }
    }.stateIn(
        scope = scope,
        started = SharingStarted.Eagerly,
        initialValue = null
    )

    override suspend fun getUserId(): Long? {
        return (userStream as kotlinx.coroutines.flow.StateFlow<CurrentUser?>).value?.id
    }

    override suspend fun setSession(user: CurrentUser) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.USER_ID] = user.id
            preferences[PreferencesKeys.USER_NAME] = user.name
            preferences[PreferencesKeys.USER_EMAIL] = user.email
        }
    }

    override suspend fun clearSession() {
        dataStore.edit { preferences ->
            preferences.remove(PreferencesKeys.USER_ID)
            preferences.remove(PreferencesKeys.USER_NAME)
            preferences.remove(PreferencesKeys.USER_EMAIL)
        }
    }
}