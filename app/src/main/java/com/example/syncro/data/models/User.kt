package com.example.syncro.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val user_id: Long,
    val email: String,
    val password_hash: String,
    val name: String
): java.io.Serializable