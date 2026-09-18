package com.example.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class File(
    @PrimaryKey(true) val id: Long? = null,
    val groupId: Long,
    val taskId: Long,
    val path: String
)
