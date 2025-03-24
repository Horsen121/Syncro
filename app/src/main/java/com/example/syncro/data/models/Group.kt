package com.example.syncro.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Group(
    @PrimaryKey(autoGenerate = true) val group_id : Long? = null,
    val name: String,
    val description: String,
    val created_at: String,
    val created_by: Long,
    val isAdmin: Boolean
): java.io.Serializable
