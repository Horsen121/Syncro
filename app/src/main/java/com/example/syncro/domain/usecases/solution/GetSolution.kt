package com.example.syncro.domain.usecases.solution

import com.example.database.entity.Solution
import com.example.syncro.domain.repository.SolutionRepository

class GetSolution(
    private val repository: SolutionRepository
) {
    suspend operator fun invoke(groupId: Long, taskId: Long, solId: Long): Solution? {
        return repository.getSolutionById(groupId, taskId, solId)
    }
}