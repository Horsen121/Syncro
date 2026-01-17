package com.example.syncro.presentation.viewmodels.group

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.application.CurrentUser
import com.example.syncro.data.datasourse.remote.RemoteApi
import com.example.syncro.data.datasourse.remote.models.CreateSolutionRequest
import com.example.syncro.data.datasourse.remote.models.UpdSolutionRequest
import com.example.syncro.data.models.Solution
import com.example.syncro.domain.usecases.SolutionUseCases
import com.example.syncro.domain.usecases.SourceFileUseCases
import com.example.syncro.utils.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class AddEditSolutionViewModel @Inject constructor(
    private val tokenManager: TokenManager,
    private val solutionUseCases: SolutionUseCases,
    private val sourceFileUseCases: SourceFileUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val token = mutableStateOf("")

    private var groupId: Long? = null
    private var taskId: Long? = null
    private var solutionId: Long? = null

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

        viewModelScope.launch {
            token.value = tokenManager.getAccessToken() ?: ""
        }.let {
            if (groupId != null && taskId != null && solutionId != null) {
                runBlocking {
                    RemoteApi.retrofitService.getSolutionById(token.value, groupId!!, taskId!!, solutionId!!).let {
                        if (it.isSuccessful) {
                            runBlocking {
                                it.body().let { solution ->
                                    _name.value = solution!!.title
                                    _desc.value = solution.description
                                }
                            }
                        } else {
                            solutionUseCases.getSolution(groupId!!, taskId!!, solutionId!!).let { solution ->
                                _name.value = solution!!.title
                                _desc.value = solution.description
                            }
                        }
                    }

                    sourceFileUseCases.getSourceFiles(groupId!!, taskId!!, solutionId!!).collect {
                        _sources.value += it.map { f -> f.path }
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
            if (groupId != null && taskId != null && solutionId != null) {
                val body = UpdSolutionRequest(
                    title = _name.value,
                    description = _desc.value
                )
                RemoteApi.retrofitService.updSolutionById(token.value, groupId!!, taskId!!, solutionId!!, body).let {
                    if (it.isSuccessful) {
                        runBlocking {
                            solutionUseCases.addSolution(
                                Solution(
                                    solution_id = solutionId,
                                    group_id = groupId!!,
                                    task_id = taskId!!,
                                    user_id = CurrentUser.id,
                                    title = _name.value,
                                    description = _desc.value
                                )
                            )
                        }
//                            .let { id ->
//                            _sources.value.forEach { file ->
//                                sourceFileUseCases.addSourceFile(
//                                    SourceFile(
//                                        group_id = groupId,
//                                        task_id = taskId,
//                                        solution_id = id!!,
//                                        path = file
//                                    )
//                                )
//                            }
//                        }
                    }
                }
            } else if(groupId != null && taskId != null) {
                val body = CreateSolutionRequest(
                    title = _name.value,
                    description = _desc.value
                )
                RemoteApi.retrofitService.addSolutionToTask(token.value, groupId!!, taskId!!, body).let {
                    if (it.isSuccessful) {
                        runBlocking {
                        it.body().let { solution ->
                            solutionUseCases.addSolution(
                                Solution(
                                    solution_id = solution!!.solution_id,
                                    group_id = groupId!!,
                                    task_id = taskId!!,
                                    user_id = CurrentUser.id,
                                    title = _name.value,
                                    description = _desc.value
                                )
                            )
                        }
//                                .let { id ->
//                                _sources.value.forEach { file ->
//                                    sourceFileUseCases.addSourceFile(
//                                        SourceFile(
//                                            group_id = groupId,
//                                            task_id = taskId,
//                                            solution_id = id!!,
//                                            path = file
//                                        )
//                                    )
//                                }
//                            }
                        }
                    }
                }
            }
        }
    }

    fun clear() {
        _name.value = ""
        _desc.value = ""
        _sources.value = emptyList()
    }

}