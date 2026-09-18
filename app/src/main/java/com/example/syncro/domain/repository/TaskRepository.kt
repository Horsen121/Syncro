package com.example.syncro.domain.repository

import com.example.database.entity.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getAllTasks(): Flow<List<Task>>
    fun getTasksByGroup(id: Long): Flow<List<Task>>
    suspend fun getTaskById(groupId: Long, taskId: Long): Task?
    suspend fun insertTask(item: Task): Long?
    suspend fun deleteTask(item: Task)
}