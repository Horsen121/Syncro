package com.example.syncro.domain.usecases.file

import com.example.syncro.data.models.File
import com.example.syncro.domain.repository.FileRepository

class AddFile(
    private val repository: FileRepository
) {
    @Throws(Exception::class)
    suspend operator fun invoke(item: File): Long {
        return repository.insertFile(item)
    }
}