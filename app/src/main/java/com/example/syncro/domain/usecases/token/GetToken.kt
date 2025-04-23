package com.example.syncro.domain.usecases.token

import com.example.syncro.data.models.Token
import com.example.syncro.domain.repository.TokenRepository

class GetToken(
    private val repository: TokenRepository
) {
    suspend operator fun invoke(): Token? {
        return repository.getToken()
    }
}