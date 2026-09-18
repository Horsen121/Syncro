package com.example.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["group_id"], unique = false)])
data class User(
    @PrimaryKey val userId: Long? = null,
    val groupId: Long,
    val email: String,
    val fullName: String,
    val isAdmin: Boolean = false
)