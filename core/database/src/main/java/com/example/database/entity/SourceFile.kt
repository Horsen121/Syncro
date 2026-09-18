package com.example.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SourceFile(
    @PrimaryKey(true) val id: Long? = null,
    val groupId: Long,
    val taskId: Long,
    val solutionId: Long,
    val path: String
)