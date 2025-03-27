package com.example.syncro.domain.repository

import com.example.syncro.data.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsersByGroup(id: Long): Flow<List<User>>
    suspend fun getUserById(id: Long): User?
    suspend fun insertUser(item: User): Long?
    suspend fun deleteUser(item: User)
}