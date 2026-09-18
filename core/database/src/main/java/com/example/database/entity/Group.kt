package com.example.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Group(
    @PrimaryKey(autoGenerate = true) val groupId : Long? = null,
    val name: String,
    val description: String,
    val membersCount: Int,
    val createdBy: Long,
    val isAdmin: Boolean,
    val isMember: Boolean = true
)
