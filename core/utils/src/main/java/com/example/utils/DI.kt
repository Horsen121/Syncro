package com.example.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.utils.data.CryptoManager
import com.example.utils.data.TokenManager
import com.example.utils.strings.AndroidStringResourceProvider
import com.example.utils.strings.StringResourceProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "user_prefs")
@Module
@InstallIn(SingletonComponent::class)
object SecurityModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }

    @Provides
    @Singleton
    fun provideCryptoManager(@ApplicationContext context: Context): CryptoManager {
        return CryptoManager(context)
    }

    @Provides
    @Singleton
    fun provideTokenManager(
        dataStore: DataStore<Preferences>,
        cryptoManager: CryptoManager
    ): TokenManager {
        return TokenManager(dataStore, cryptoManager)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class UiModule {
    @Binds
    @Singleton
    abstract fun bindStringResourceProvider(
        impl: AndroidStringResourceProvider
    ): StringResourceProvider
}