package com.example.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class SourceFile(
    val id: Long? = null,
    val groupId: Long,
    val taskId: Long,
    val solutionId: Long,
    val path: String
)
