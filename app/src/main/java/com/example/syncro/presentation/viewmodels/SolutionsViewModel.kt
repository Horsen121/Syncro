package com.example.syncro.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.data.datasourse.remote.RemoteApi
import com.example.syncro.data.models.Solution
import com.example.syncro.domain.usecases.SolutionUseCases
import com.example.syncro.utils.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SolutionsViewModel @Inject constructor(
    private val tokenManager: TokenManager,
    private val solutionUseCases: SolutionUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val token = mutableStateOf("")

    private var groupId = -1L
    private var taskId = -1L

    private var _solutions = MutableStateFlow<List<Solution>>(emptyList())
    val solutions: StateFlow<List<Solution>> = _solutions

    init {
        viewModelScope.launch {
            token.value = tokenManager.getAccessToken() ?: ""

            savedStateHandle.get<Long?>("groupId").let { id ->
                if (id != null && id != -1L) {
                    groupId = id
                }
            }
            savedStateHandle.get<Long?>("taskId").let { id ->
                if (id != null && id != -1L) {
                    taskId = id
                }
            }
        }.let {
            loadSolutions()
        }
    }

    fun getGroupId() = groupId
    fun getTaskId() = taskId

    fun update() {
        loadSolutions()
    }

    private fun loadSolutions() {
        viewModelScope.launch {
            RemoteApi.retrofitService.getSolutionsByTask(token.value, groupId, taskId).let {
                if (it.isSuccessful) {
                    _solutions.value = it.body()!!
                } else {
                    solutionUseCases.getSolutions(taskId).onEach { list ->
                        _solutions.value = list
                    }
                }
            }
        }

    }
}