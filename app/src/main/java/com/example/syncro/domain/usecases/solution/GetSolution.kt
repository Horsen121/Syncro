package com.example.syncro.domain.usecases.solution

import com.example.syncro.data.models.Solution
import com.example.syncro.domain.repository.SolutionRepository

class GetSolution(
    private val repository: SolutionRepository
) {
    suspend operator fun invoke(id: Long): Solution? {
        return repository.getSolutionById(id)
    }
}