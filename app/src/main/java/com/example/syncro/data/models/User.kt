package com.example.syncro.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val user_id: Long,
    val email: String,
    val password_hash: String,
    val full_name: String,
    val created_at: String,
    val last_login: String
): java.io.Serializable