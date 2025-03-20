package com.example.syncro.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Solution(
    @PrimaryKey val solution_id: Int,
    val task_id: Int,
    val user_id: Int,
    val title: String,
    val description: String,
    val created_at: Date,
    val updated_at: Date
)