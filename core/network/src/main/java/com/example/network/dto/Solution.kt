package com.example.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class Solution(
    val solutionId: Long? = null,
    val groupId: Long,
    val taskId: Long,
    val userId: Long,
    val title: String,
    val description: String
)

@Serializable
data class CreateSolutionRequest(
    val title: String,
    val description: String
)

@Serializable
data class UpdSolutionRequest(
    val title: String,
    val description: String
)