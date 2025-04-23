package com.example.syncro.domain.usecases.source_file

import com.example.syncro.data.models.SourceFile
import com.example.syncro.domain.repository.SourceFileRepository

class AddSourceFile(
    private val repository: SourceFileRepository
) {
    @Throws(Exception::class)
    suspend operator fun invoke(item: SourceFile): Long {
        return repository.insertSourceFile(item)
    }
}