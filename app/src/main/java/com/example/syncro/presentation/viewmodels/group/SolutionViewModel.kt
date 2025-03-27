package com.example.syncro.presentation.viewmodels.group

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.data.models.Solution
import com.example.syncro.domain.usecases.SolutionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SolutionViewModel @Inject constructor(
    private val solutionUseCases: SolutionUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var groupId = -1L
    private var taskId = -1L
    private var solutionId: Long? = null

    private var _task = mutableStateOf("")
    val task: State<String> = _task

    private var _name = mutableStateOf("")
    val name: State<String> = _name

    private var _desc = mutableStateOf("")
    val desc: State<String> = _desc

    private var _files = mutableStateOf<List<String>>(emptyList())
    val files: State<List<String>> = _files

    private var _sources = mutableStateOf<List<String>>(emptyList())
    val sources: State<List<String>> = _sources

    init {
        savedStateHandle.get<Long>("groupId").let { id ->
            if (id != null && id != -1L) {
                groupId = id
            }
        }
        savedStateHandle.get<Long>("taskId").let { id ->
            if (id != null && id != -1L) {
                taskId = id
            }
        }
        savedStateHandle.get<Long>("solutionId").let { id ->
            if (id != null && id != -1L) {
                solutionId = id
                viewModelScope.launch {
                    solutionUseCases.getSolution(id)?.also { solution ->
                        _name.value = solution.title
                        _desc.value = solution.description
                    }
                }
            }
        }
    }

    fun onNameChange(text: String) {
        _name.value = text
    }

    fun onDescChange(text: String) {
        _desc.value = text
    }

    fun onSourceAdd(source: String) {
        _sources.value += source
    }

    fun onSourceRemove(source: String) {
        _sources.value -= source
    }

    fun onSave() {
        viewModelScope.launch {
            solutionUseCases.addSolution(
                Solution(
                    solution_id = solutionId,
                    task_id = taskId,
                    user_id = 1, // TODO: change to
                    title = _name.value,
                    description = _desc.value
                )
            )
        }
    }

    fun getGroupId() = groupId
    fun getTaskId() = taskId

    fun clear() {
        _name.value = ""
        _desc.value = ""
        _files.value = emptyList()
        _sources.value = emptyList()
    }

}