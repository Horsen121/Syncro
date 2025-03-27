package com.example.syncro.data.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["group_id"], unique = false)])
data class User(
    @PrimaryKey val user_id: Long? = null,
    val group_id: Long,
    val email: String,
    val name: String,
    val isAdmin: Boolean = false
): java.io.Serializable