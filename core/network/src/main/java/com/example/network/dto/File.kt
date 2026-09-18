package com.example.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class File(
    val id: Long? = null,
    val groupId: Long,
    val taskId: Long,
    val path: String
)
