package com.example.syncro.domain.usecases.file

import com.example.database.entity.File
import com.example.syncro.domain.repository.FileRepository
import kotlinx.coroutines.flow.Flow

class GetFiles(
    private val repository: FileRepository
) {
    operator fun invoke(group: Long, task: Long): Flow<List<File>> {
        return repository.getFiles(group, task)
    }
}