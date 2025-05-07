package com.example.syncro.domain.repository

import com.example.syncro.data.models.Solution
import kotlinx.coroutines.flow.Flow

interface SolutionRepository {
    fun getSolutionsByTask(id: Long): Flow<List<Solution>>
    suspend fun getSolutionById(groupId: Long, taskId: Long, solId: Long): Solution?
    suspend fun insertSolution(item: Solution): Long?
    suspend fun deleteSolution(item: Solution)
}