package com.example.syncro.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class SourceFile(
    @PrimaryKey(true) val id: Long? = null,
    val group_id: Long,
    val task_id: Long,
    val solution_id: Long,
    val path: String
): Serializable