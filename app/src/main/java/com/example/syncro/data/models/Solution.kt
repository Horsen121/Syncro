package com.example.syncro.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
@kotlinx.serialization.Serializable
data class Solution(
    @PrimaryKey(autoGenerate = true) val solution_id: Long? = null,
    val group_id: Long,
    val task_id: Long,
    val user_id: Long,
    val title: String,
    val description: String
)