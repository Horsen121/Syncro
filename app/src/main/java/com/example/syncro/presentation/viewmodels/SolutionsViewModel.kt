package com.example.syncro.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.data.models.Solution
import com.example.syncro.domain.usecases.SolutionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SolutionsViewModel @Inject constructor(
    private val solutionUseCases: SolutionUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var groupId = -1L
    private var taskId = -1L

    private var _solutions = mutableStateOf<List<Solution>>(emptyList())
    val solutions: State<List<Solution>> = _solutions

    init {
        savedStateHandle.get<Long?>("taskId").let { id ->
            if (id != null && id != -1L) {
                taskId = id
            }
            loadSolutions()
        }
        savedStateHandle.get<Long?>("groupId").let { id ->
            if (id != null && id != -1L) {
                groupId = id
            }
        }
    }

    fun getGroupId() = groupId
    fun getTaskId() = taskId

    fun update() {
        loadSolutions()
    }

    private fun loadSolutions() {
        solutionUseCases.getSolutions(taskId).onEach {
            _solutions.value = it
        }.launchIn(viewModelScope)
    }
}