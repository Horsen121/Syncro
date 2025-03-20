package com.example.syncro.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class User(
    @PrimaryKey val user_id: Int,
    val email: String,
    val password_hash: String,
    val full_name: String,
    val created_at: Date,
    val last_login: Date
)