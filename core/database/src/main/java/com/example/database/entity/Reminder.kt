package com.example.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Reminder(
    @PrimaryKey val reminderId: Long? = null,
    val taskId: Long,
    val groupId: Long,
    val reminderTime: String,
    val message: String,
    val isSent: Boolean
)