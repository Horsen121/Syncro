package com.example.syncro.domain.usecases

import com.example.syncro.domain.usecases.task.AddTask
import com.example.syncro.domain.usecases.task.DeleteTask
import com.example.syncro.domain.usecases.task.GetTask
import com.example.syncro.domain.usecases.task.GetTasks

data class TaskUseCases(
    val getTasks: GetTasks,
    val deleteTask: DeleteTask,
    val addTask: AddTask,
    val getTask: GetTask,
)