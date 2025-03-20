package com.example.syncro.domain.repository

import com.example.syncro.data.models.Group
import kotlinx.coroutines.flow.Flow

interface GroupRepository {
    fun getGroups(): Flow<List<Group>>
    suspend fun getGroupById(id: Long): Group?
    suspend fun insertGroup(item: Group): Long?
    suspend fun deleteGroup(item: Group)
}