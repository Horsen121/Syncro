package com.example.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val taskId: Long? = null,
    val groupId: Long,
    val title: String,
    val description: String,
    val createdBy: Long,
    val startTime: String,
    val endTime: String,
    val priority: String,
    val isCompleted: Boolean = false
)

@Serializable
data class CreateTaskRequest(
    val title: String,
    val description: String,
    val startTime: String,
    val endTime: String,
    val priority: String,
    val context: String
)