package com.example.syncro.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
@kotlinx.serialization.Serializable
data class Group(
    @PrimaryKey(autoGenerate = true) val group_id : Long? = null,
    val name: String,
    val description: String,
    val members_count: Int,
    val created_by: Long,
    val is_admin: Boolean,
    val is_member: Boolean = true
)
