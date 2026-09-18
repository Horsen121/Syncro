package com.example.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Solution(
    @PrimaryKey(autoGenerate = true) val solutionId: Long? = null,
    val groupId: Long,
    val taskId: Long,
    val userId: Long,
    val title: String,
    val description: String
)