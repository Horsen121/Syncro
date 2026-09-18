package com.example.syncro.domain.usecases.task

import com.example.database.entity.Task
import com.example.syncro.domain.repository.TaskRepository

class GetTask(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(groupId: Long, taskId: Long): Task? {
        return repository.getTaskById(groupId, taskId)
    }
}