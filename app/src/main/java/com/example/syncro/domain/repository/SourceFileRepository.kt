package com.example.syncro.domain.repository

import com.example.database.entity.SourceFile
import kotlinx.coroutines.flow.Flow

interface SourceFileRepository {
    fun getSourceFiles(group:Long, task: Long, solution: Long): Flow<List<SourceFile>>
    suspend fun insertSourceFile(item: SourceFile): Long
}