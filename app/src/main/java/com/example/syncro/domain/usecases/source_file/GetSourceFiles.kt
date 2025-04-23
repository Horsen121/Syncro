package com.example.syncro.domain.usecases.source_file

import com.example.syncro.data.models.SourceFile
import com.example.syncro.domain.repository.SourceFileRepository
import kotlinx.coroutines.flow.Flow

class GetSourceFiles(
    private val repository: SourceFileRepository
) {
    operator fun invoke(group: Long, task: Long, solution: Long): Flow<List<SourceFile>> {
        return repository.getSourceFiles(group, task, solution)
    }
}