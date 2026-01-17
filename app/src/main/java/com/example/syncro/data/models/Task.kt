package com.example.syncro.data.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["group_id"], unique = false)])
@kotlinx.serialization.Serializable
data class Task(
    @PrimaryKey(autoGenerate = true) val taskId: Long? = null,
    val groupId: Long,
    val title: String,
    val description: String,
    val createdBy: Long,
    val startTime: String,
    val endTime: String,
    val priority: String,
    val isCompleted: Boolean = false
)