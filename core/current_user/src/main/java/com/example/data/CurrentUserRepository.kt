package com.example.data

import kotlinx.coroutines.flow.Flow

interface CurrentUserRepository {
    val userStream: Flow<CurrentUser?>

    suspend fun getUserId(): Long?

    suspend fun setSession(user: CurrentUser)

    suspend fun clearSession()
}