package com.example.syncro.data.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["group_id"], unique = false)])
@kotlinx.serialization.Serializable
data class User(
    @PrimaryKey val user_id: Long? = null,
    val group_id: Long,
    val email: String,
    val full_name: String,
    val is_admin: Boolean = false
)