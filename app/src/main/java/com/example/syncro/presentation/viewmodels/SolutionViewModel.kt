package com.example.syncro.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.domain.usecases.SolutionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SolutionViewModel @Inject constructor(
    private val solutionUseCases: SolutionUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _task = mutableStateOf("")
    val task: State<String> = _task

    private var _tasks = mutableStateOf<List<String>>(emptyList())
    val tasks: State<List<String>> = _tasks

    private var _open = mutableStateOf(false)
    val open: State<Boolean> = _open

    private var _name = mutableStateOf("")
    val name: State<String> = _name

    private var _desc = mutableStateOf("")
    val desc: State<String> = _desc

    private var _files = mutableStateOf<List<String>>(emptyList())
    val files: State<List<String>> = _files

    private var _sources = mutableStateOf<List<String>>(emptyList())
    val sources: State<List<String>> = _sources

    init {
        savedStateHandle.get<Long>("taskId").let { id ->
            if (id != null && id != -1L) {
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

    fun onOpenChange(open: Boolean) {
        _open.value = open
    }

    fun onTaskChange(text: String) {
        _task.value = text
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

    fun clear() {
        _name.value = ""
        _desc.value = ""
        _files.value = emptyList()
        _sources.value = emptyList()
    }

}