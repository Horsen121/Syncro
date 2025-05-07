package com.example.syncro.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
@kotlinx.serialization.Serializable
data class File(
    @PrimaryKey(true) val id: Long? = null,
    val group_id: Long,
    val task_id: Long,
    val path: String
)
