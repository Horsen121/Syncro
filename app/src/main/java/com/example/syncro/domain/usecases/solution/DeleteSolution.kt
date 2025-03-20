package com.example.syncro.domain.usecases.solution

import com.example.syncro.data.models.Solution
import com.example.syncro.domain.repository.SolutionRepository

class DeleteSolution(
    private val repository: SolutionRepository
) {
    suspend operator fun invoke(solution: Solution) {
        repository.deleteSolution(solution)
    }
}