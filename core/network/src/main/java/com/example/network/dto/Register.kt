package com.example.network.dto


data class RegisterRequest(
    val email: String,
    val password: String,
    val fullName: String
)