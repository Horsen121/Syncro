package com.example.syncro.domain.usecases.task

import com.example.syncro.data.models.Task
import com.example.syncro.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasks(
    private val repository: TaskRepository
) {
    operator fun invoke(id: Long): Flow<List<Task>> {
        return repository.getTasksByGroup(id)
    }
}