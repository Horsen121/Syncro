package com.example.syncro.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Task(
    @PrimaryKey val task_id: Int,
    val group_id: Int,
    val title: String,
    val description: String,
    val created_at: Date,
    val created_by: Int,
    val start_time: Date,
    val end_time: Date,
    val priority: String
)