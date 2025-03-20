package com.example.syncro.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Reminder(
    @PrimaryKey val reminder_id: Int,
    val task_id: Int,
    val group_id: Int,
    val reminder_time: Date,
    val created_at: Date,
    val message: String,
    val is_sent: Boolean
)