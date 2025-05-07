package com.example.syncro.presentation.viewmodels.group

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.application.CurrentUser
import com.example.syncro.data.models.Solution
import com.example.syncro.data.models.SourceFile
import com.example.syncro.domain.usecases.SolutionUseCases
import com.example.syncro.domain.usecases.SourceFileUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SolutionViewModel @Inject constructor(
    private val solutionUseCases: SolutionUseCases,
    private val sourceFileUseCases: SourceFileUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var groupId = -1L
    var taskId = -1L
    var solutionId: Long? = null

    private var _task = mutableStateOf("")
    val task: State<String> = _task

    private var _name = mutableStateOf("")
    val name: State<String> = _name

    private var _desc = mutableStateOf("")
    val desc: State<String> = _desc

    private var _sources = mutableStateOf<List<String>>(emptyList())
    val sources: State<List<String>> = _sources

    init {
        runBlocking {
            savedStateHandle.get<Long>("groupId").let { id ->
                if (id != null && id != -1L) {
                    groupId = id
                }
            }
        }
        runBlocking {
            savedStateHandle.get<Long>("taskId").let { id ->
                if (id != null && id != -1L) {
                    taskId = id
                }
            }
        }
        runBlocking {
            savedStateHandle.get<Long>("solutionId").let { id ->
                if (id != null && id != -1L) {
                    solutionId = id
                    viewModelScope.launch {
                        solutionUseCases.getSolution(groupId,taskId,id)?.also { solution ->
                            _name.value = solution.title
                            _desc.value = solution.description
                        }
                    }
                }
            }
        }
        if (groupId != null && taskId != null && solutionId != null) {
            viewModelScope.launch {
                sourceFileUseCases.getSourceFiles(groupId, taskId, solutionId!!).collect {
                    _sources.value += it.map { f -> f.path }
                }
            }
        }
    }
}