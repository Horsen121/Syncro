package com.example.syncro.domain.usecases.solution

import com.example.syncro.data.models.Solution
import com.example.syncro.domain.repository.SolutionRepository

class AddSolution(
    private val repository: SolutionRepository
) {
    @Throws(Exception::class)
    suspend operator fun invoke(solution: Solution): Long? {
        return repository.insertSolution(solution)
    }
}