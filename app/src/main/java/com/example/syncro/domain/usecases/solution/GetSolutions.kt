package com.example.syncro.domain.usecases.solution

import com.example.syncro.data.models.Solution
import com.example.syncro.domain.repository.SolutionRepository
import kotlinx.coroutines.flow.Flow

class GetSolutions(
    private val repository: SolutionRepository
) {
    operator fun invoke(id: Long): Flow<List<Solution>> {
        return repository.getSolutionsByTask(id)
    }
}