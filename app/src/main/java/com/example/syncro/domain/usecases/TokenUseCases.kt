package com.example.syncro.domain.usecases

import com.example.syncro.domain.usecases.token.AddToken
import com.example.syncro.domain.usecases.token.GetToken

data class TokenUseCases(
    val getToken: GetToken,
    val addToken: AddToken,
)