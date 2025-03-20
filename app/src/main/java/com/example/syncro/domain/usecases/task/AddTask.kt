package com.example.syncro.domain.usecases.task

import com.example.syncro.data.models.Task
import com.example.syncro.domain.repository.TaskRepository

class AddTask(
    private val repository: TaskRepository
) {
    @Throws(Exception::class)
    suspend operator fun invoke(item: Task): Long? {
        return repository.insertTask(item)
    }
}