package com.example.syncro.domain.repository

import com.example.database.entity.File
import kotlinx.coroutines.flow.Flow

interface FileRepository {
    fun getFiles(group:Long, task: Long): Flow<List<File>>
    suspend fun insertFile(item: File): Long
}