package com.example.syncro.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Reminder(
    @PrimaryKey val reminder_id: Long? = null,
    val task_id: Long,
    val group_id: Long,
    val reminder_time: String,
    val message: String,
    val is_sent: Boolean
): java.io.Serializable