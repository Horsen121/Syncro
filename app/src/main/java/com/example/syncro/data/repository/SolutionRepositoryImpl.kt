package com.example.syncro.data.repository

import com.example.database.dao.SolutionDao
import com.example.database.entity.Solution
import com.example.syncro.domain.repository.SolutionRepository
import kotlinx.coroutines.flow.Flow

class SolutionRepositoryImpl(
    private val daoLocal: SolutionDao
): SolutionRepository {
    override fun getSolutionsByTask(id: Long): Flow<List<Solution>> {
        return daoLocal.getSolutions(id)
    }

    override suspend fun getSolutionById(groupId: Long, taskId: Long, solId: Long): Solution? {
        return daoLocal.getSolutionById(groupId, taskId, solId)
    }

    override suspend fun insertSolution(item: Solution): Long {
        return daoLocal.insertSolution(item)
    }

    override suspend fun deleteSolution(item: Solution) {
        return daoLocal.deleteSolution(item)
    }
}