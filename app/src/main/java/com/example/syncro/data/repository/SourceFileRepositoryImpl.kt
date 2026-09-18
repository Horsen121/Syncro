package com.example.syncro.data.repository

import com.example.database.dao.SourceFileDao
import com.example.database.entity.SourceFile
import com.example.syncro.domain.repository.SourceFileRepository
import kotlinx.coroutines.flow.Flow

class SourceFileRepositoryImpl(
    private val daoLocal: SourceFileDao
): SourceFileRepository {
    override fun getSourceFiles(group: Long, task: Long, solution: Long): Flow<List<SourceFile>> {
        return daoLocal.getSourceFiles(group, task, solution)
    }

    override suspend fun insertSourceFile(item: SourceFile): Long {
        return daoLocal.insertSourceFile(item)
    }
}