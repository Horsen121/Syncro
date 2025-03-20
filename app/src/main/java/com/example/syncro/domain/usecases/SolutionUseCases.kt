package com.example.syncro.domain.usecases

import com.example.syncro.domain.usecases.solution.AddSolution
import com.example.syncro.domain.usecases.solution.DeleteSolution
import com.example.syncro.domain.usecases.solution.GetSolution
import com.example.syncro.domain.usecases.solution.GetSolutions

data class SolutionUseCases(
    val getSolutions: GetSolutions,
    val deleteSolution: DeleteSolution,
    val addSolution: AddSolution,
    val getSolution: GetSolution,
)