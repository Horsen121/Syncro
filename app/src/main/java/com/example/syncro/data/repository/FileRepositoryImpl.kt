package com.example.syncro.data.repository

import com.example.database.dao.FileDao
import com.example.database.entity.File
import com.example.syncro.domain.repository.FileRepository
import kotlinx.coroutines.flow.Flow

class FileRepositoryImpl(
    private val daoLocal: FileDao
): FileRepository {
    override fun getFiles(group: Long, task: Long): Flow<List<File>> {
        return daoLocal.getFiles(group, task)
    }

    override suspend fun insertFile(item: File): Long {
        return daoLocal.insertFile(item)
    }
}