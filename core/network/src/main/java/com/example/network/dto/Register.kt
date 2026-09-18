package com.example.network.dto


data class RegisterRequest(
    val email: String,
    val password: String,
    val full_name: String
)
data class RegisterResponse(
    val access_token: String,
    val refresh_token: String,
    val user_id: Long,
    val full_name: String
)