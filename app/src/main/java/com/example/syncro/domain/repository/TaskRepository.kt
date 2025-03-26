package com.example.syncro.domain.repository

import com.example.syncro.data.models.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getAllTasks(): Flow<List<Task>>
    fun getTasksByGroup(id: Long): Flow<List<Task>>
    suspend fun getTaskById(id: Long): Task?
    suspend fun insertTask(item: Task): Long?
    suspend fun deleteTask(item: Task)
}