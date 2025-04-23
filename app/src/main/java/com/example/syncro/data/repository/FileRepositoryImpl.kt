package com.example.syncro.data.repository

import com.example.syncro.data.datasourse.local.dao.FileDaoLocal
import com.example.syncro.data.models.File
import com.example.syncro.domain.repository.FileRepository
import kotlinx.coroutines.flow.Flow

class FileRepositoryImpl(
    private val daoLocal: FileDaoLocal
): FileRepository {
    override fun getFiles(group: Long, task: Long): Flow<List<File>> {
        return daoLocal.getFiles(group, task)
    }

    override suspend fun insertFile(item: File): Long {
        return daoLocal.insertFile(item)
    }
}