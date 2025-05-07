package com.example.syncro.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
@kotlinx.serialization.Serializable
data class Token(
    @PrimaryKey val id: Int = 1,
    val token: String,
    val userId: Long,
    val name: String,
    val email: String
)