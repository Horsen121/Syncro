package com.example.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class CurrentUser(
    val accessToken: String,
    val refreshToken: String,
    val userId: Long,
    val fullName: String
)