package com.example.syncro.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Token(
    @PrimaryKey val id: Int = 1,
    val token: String
): Serializable