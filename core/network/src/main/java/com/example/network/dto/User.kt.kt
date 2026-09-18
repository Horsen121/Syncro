package com.example.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val userId: Long? = null,
    val groupId: Long,
    val email: String,
    val fullName: String,
    val isAdmin: Boolean = false
)