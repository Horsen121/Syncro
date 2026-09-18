package com.example.network.dto

data class LoginRequest(
    val email: String,
    val password: String
)
data class LoginResponse(
    val access_token: String,
    val refresh_token: String,
    val user_id: Long,
    val full_name: String
)