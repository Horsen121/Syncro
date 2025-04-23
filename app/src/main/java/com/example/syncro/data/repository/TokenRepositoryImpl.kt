package com.example.syncro.data.repository

import com.example.syncro.data.datasourse.local.dao.TokenDaoLocal
import com.example.syncro.data.models.Token
import com.example.syncro.domain.repository.TokenRepository

class TokenRepositoryImpl(
    private val daoLocal: TokenDaoLocal
): TokenRepository {
    override suspend fun getToken(): Token {
        return daoLocal.getToken()
    }

    override suspend fun insertToken(item: Token): Long {
        return daoLocal.insertToken(item)
    }
}