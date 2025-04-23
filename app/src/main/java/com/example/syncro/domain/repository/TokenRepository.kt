package com.example.syncro.domain.repository

import com.example.syncro.data.models.Token

interface TokenRepository {
    suspend fun getToken(): Token?
    suspend fun insertToken(item: Token): Long?
}