package com.example.syncro.data.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["group_id"], unique = true)])
data class Task( // TODO: add parameters
    @PrimaryKey val task_id: Long? = null,
    val group_id: Long,
    val title: String,
    val description: String,
    val created_at: String,
    val created_by: Long,
    val start_time: String,
    val end_time: String,
    val priority: String
): java.io.Serializable