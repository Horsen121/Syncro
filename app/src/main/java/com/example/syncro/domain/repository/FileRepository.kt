package com.example.syncro.domain.repository

import com.example.syncro.data.models.File
import kotlinx.coroutines.flow.Flow

interface FileRepository {
    fun getFiles(group:Long, task: Long): Flow<List<File>>
    suspend fun insertFile(item: File): Long
}