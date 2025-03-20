package com.example.syncro.data.repository

import com.example.syncro.data.datasourse.local.dao.SolutionDaoLocal
import com.example.syncro.data.models.Solution
import com.example.syncro.domain.repository.SolutionRepository
import kotlinx.coroutines.flow.Flow

class SolutionRepositoryImpl(
    private val daoLocal: SolutionDaoLocal
): SolutionRepository {
    override fun getSolutionsByTask(id: Long): Flow<List<Solution>> {
        return daoLocal.getSolutions(id)
    }

    override suspend fun getSolutionById(id: Long): Solution? {
        return daoLocal.getSolutionById(id)
    }

    override suspend fun insertSolution(item: Solution): Long {
        return daoLocal.insertSolution(item)
    }

    override suspend fun deleteSolution(item: Solution) {
        return daoLocal.deleteSolution(item)
    }
}