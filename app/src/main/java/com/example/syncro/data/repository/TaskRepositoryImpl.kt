package com.example.syncro.data.repository

import com.example.syncro.data.datasourse.local.dao.TaskDaoLocal
import com.example.syncro.data.models.Task
import com.example.syncro.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(
    private val daoLocal: TaskDaoLocal,
): TaskRepository {
    override fun getAllTasks(): Flow<List<Task>> {
        return daoLocal.getAllTasks()
    }

    override fun getTasksByGroup(id: Long): Flow<List<Task>> {
        return daoLocal.getTasks(id)
    }

    override suspend fun getTaskById(id: Long): Task? {
        return daoLocal.getTaskById(id)
    }

    override suspend fun insertTask(item: Task): Long {
        return daoLocal.insertTask(item)
    }

    override suspend fun deleteTask(item: Task) {
        return daoLocal.deleteTask(item)
    }
}