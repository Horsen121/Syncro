package com.example.syncro.domain.usecases.task

import com.example.database.entity.Task
import com.example.syncro.domain.repository.TaskRepository

class DeleteTask(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: Task) {
        repository.deleteTask(task)
    }
}