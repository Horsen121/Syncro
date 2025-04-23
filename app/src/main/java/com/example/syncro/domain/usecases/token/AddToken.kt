package com.example.syncro.domain.usecases.token

import com.example.syncro.data.models.Token
import com.example.syncro.domain.repository.TokenRepository

class AddToken(
    private val repository: TokenRepository
) {
    @Throws(Exception::class)
    suspend operator fun invoke(item: Token): Long? {
        return repository.insertToken(item)
    }
}