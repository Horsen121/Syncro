package com.example.syncro.data.repository

import com.example.syncro.data.datasourse.local.dao.SourceFileDaoLocal
import com.example.syncro.data.models.SourceFile
import com.example.syncro.domain.repository.SourceFileRepository
import kotlinx.coroutines.flow.Flow

class SourceFileRepositoryImpl(
    private val daoLocal: SourceFileDaoLocal
): SourceFileRepository {
    override fun getSourceFiles(group: Long, task: Long, solution: Long): Flow<List<SourceFile>> {
        return daoLocal.getSourceFiles(group, task, solution)
    }

    override suspend fun insertSourceFile(item: SourceFile): Long {
        return daoLocal.insertSourceFile(item)
    }
}