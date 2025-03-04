package com.example.syncro.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Group(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val isAdmin: Boolean = false
) {
    
    fun toDomain() {
        // TODO: convert to domain model 
    }
    
}
