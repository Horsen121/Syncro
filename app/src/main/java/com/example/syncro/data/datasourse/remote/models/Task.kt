package com.example.syncro.data.datasourse.remote.models

data class CreateTaskRequest(
    val title: String,
    val description: String,
    val start_time: String,
    val end_time: String,
    val priority: String,
    val context: String
)
//data class CreateTaskResponse(
//
//)