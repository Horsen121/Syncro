package com.example.syncro.presentation.viewmodels.group

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.domain.usecases.FileUseCases
import com.example.syncro.domain.usecases.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    private val fileUseCases: FileUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var groupId = -1L
    var taskId: Long? = null

    private var _name = mutableStateOf("")
    val name: State<String> = _name

    private var _desc = mutableStateOf("")
    val desc: State<String> = _desc

    private var _startTime = mutableStateOf<LocalDateTime?>(null)
    val startTime: State<LocalDateTime?> = _startTime

    private var _endTime = mutableStateOf<LocalDateTime?>(null)
    val endTime: State<LocalDateTime?> = _endTime

    private var _reminderTime = mutableStateOf<LocalDateTime?>(null)
    val reminderTime: State<LocalDateTime?> = _reminderTime

    private var _diff = mutableStateOf("")
    val diff: State<String> = _diff

    private var _files = mutableStateOf<List<String>>(emptyList())
    val files: State<List<String>> = _files

    init {
        runBlocking {
            savedStateHandle.get<Long>("groupId").let { id ->
                if (id != null) groupId = id
            }
        }
        runBlocking {
            savedStateHandle.get<Long>("taskId").let { id ->
                if (id != null && id != -1L) {
                    taskId = id
                    viewModelScope.launch {
                        taskUseCases.getTask(groupId,id)?.also { task ->
                            _name.value = task.title
                            _desc.value = task.description
                            if (task.start_time != "null") _startTime.value = LocalDateTime.parse(task.start_time)
                            if (task.end_time != "null") _endTime.value = LocalDateTime.parse(task.end_time)
                            if (task.reminderTime != "null") _reminderTime.value = LocalDateTime.parse(task.reminderTime)
                            _diff.value = task.difficult
                        }
                    }
                }
            }
        }
        if (groupId != null && taskId != null) {
            viewModelScope.launch {
                fileUseCases.getFiles(groupId, taskId!!).collect {
                    _files.value += it.map { f -> f.path }
                }
            }
        }
    }
}