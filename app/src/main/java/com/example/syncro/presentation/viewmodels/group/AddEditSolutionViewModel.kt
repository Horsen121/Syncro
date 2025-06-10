package com.example.syncro.presentation.viewmodels.group

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.application.CurrentUser
import com.example.syncro.data.datasourse.remote.RemoteApi
import com.example.syncro.data.datasourse.remote.models.CreateSolutionRequest
import com.example.syncro.data.datasourse.remote.models.UpdSolutionRequest
import com.example.syncro.data.models.Solution
import com.example.syncro.data.models.SourceFile
import com.example.syncro.domain.usecases.SolutionUseCases
import com.example.syncro.domain.usecases.SourceFileUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class AddEditSolutionViewModel @Inject constructor(
    private val solutionUseCases: SolutionUseCases,
    private val sourceFileUseCases: SourceFileUseCases,
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

    private var _sources = mutableStateOf<List<String>>(emptyList())
    val sources: State<List<String>> = _sources

    init {
        runBlocking {
            savedStateHandle.get<Long>("groupId").let { id ->
                if (id != null && id != -1L) groupId = id
            }
            savedStateHandle.get<Long>("taskId").let { id ->
                if (id != null && id != -1L) taskId = id
            }
            savedStateHandle.get<Long>("solutionId").let { id ->
                if (id != null && id != -1L) solutionId = id
            }
        }

        if (solutionId != null) {
            viewModelScope.launch {
                RemoteApi.retrofitService.getSolutionById(CurrentUser.token, groupId, taskId, solutionId!!).let {
                    if (it.isSuccessful) {
                        it.body().let { solution ->
                            _name.value = solution!!.title
                            _desc.value = solution.description
                        }
                    } else {
                        solutionUseCases.getSolution(groupId, taskId, solutionId!!).let { solution ->
                            _name.value = solution!!.title
                            _desc.value = solution.description
                        }
                    }
                }

                sourceFileUseCases.getSourceFiles(groupId, taskId, solutionId!!).collect {
                    _sources.value += it.map { f -> f.path }
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
            if (solutionId != null) {
                val body = UpdSolutionRequest(
                    title = _name.value,
                    description = _desc.value
                )
                RemoteApi.retrofitService.updSolutionById(CurrentUser.token, groupId, taskId, solutionId!!, body).let {
                    if (it.isSuccessful) {
                        solutionUseCases.addSolution(
                            Solution(
                                solution_id = solutionId,
                                group_id = groupId,
                                task_id = taskId,
                                user_id = CurrentUser.id,
                                title = _name.value,
                                description = _desc.value
                            )
                        ).let { id ->
                            _sources.value.forEach { file ->
                                sourceFileUseCases.addSourceFile(
                                    SourceFile(
                                        group_id = groupId,
                                        task_id = taskId,
                                        solution_id = id!!,
                                        path = file
                                    )
                                )
                            }
                        }
                    }
                }
            } else {
                val body = CreateSolutionRequest(
                    title = _name.value,
                    description = _desc.value
                )
                RemoteApi.retrofitService.addSolutionToTask(CurrentUser.token, groupId, taskId, body).let {
                    if (it.isSuccessful) {
                        it.body().let { solution ->
                            solutionUseCases.addSolution(
                                Solution(
                                    solution_id = solution!!.solution_id,
                                    group_id = groupId,
                                    task_id = taskId,
                                    user_id = CurrentUser.id,
                                    title = _name.value,
                                    description = _desc.value
                                )
                            ).let { id ->
                                _sources.value.forEach { file ->
                                    sourceFileUseCases.addSourceFile(
                                        SourceFile(
                                            group_id = groupId,
                                            task_id = taskId,
                                            solution_id = id!!,
                                            path = file
                                        )
                                    )
                                }
                            }
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