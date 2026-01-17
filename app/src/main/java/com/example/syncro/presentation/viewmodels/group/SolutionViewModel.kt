package com.example.syncro.presentation.viewmodels.group

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.domain.usecases.SolutionUseCases
import com.example.syncro.domain.usecases.SourceFileUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SolutionViewModel @Inject constructor(
    private val solutionUseCases: SolutionUseCases,
    private val sourceFileUseCases: SourceFileUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var groupId: Long? = null
    var taskId: Long? = null
    var solutionId: Long? = null

//    private var _task = MutableStateFlow("")
//    val task: StateFlow<String> = _task

    private var _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private var _desc = MutableStateFlow("")
    val desc: StateFlow<String> = _desc

    private var _sources = MutableStateFlow<List<String>>(emptyList())
    val sources: StateFlow<List<String>> = _sources

    init {
        groupId = savedStateHandle["groupId"]
        taskId = savedStateHandle["taskId"]
        solutionId = savedStateHandle["solutionId"]

        if (groupId != null && taskId != null && solutionId != null) {
            viewModelScope.launch {
                solutionUseCases.getSolution(groupId!!,taskId!!,solutionId!!)?.also { solution ->
                    _name.value = solution.title
                    _desc.value = solution.description
                }
            }
            viewModelScope.launch {
                sourceFileUseCases.getSourceFiles(groupId!!, taskId!!, solutionId!!).collect {
                    _sources.value += it.map { f -> f.path }
                }
            }
        }
    }
}