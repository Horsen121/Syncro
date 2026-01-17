package com.example.syncro.presentation.viewmodels.group

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.domain.usecases.FileUseCases
import com.example.syncro.domain.usecases.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    private val fileUseCases: FileUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var groupId: Long? = null
    var taskId: Long? = null

    private var _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private var _desc = MutableStateFlow("")
    val desc: StateFlow<String> = _desc

    private var _startTime = MutableStateFlow<LocalDateTime?>(null)
    val startTime: StateFlow<LocalDateTime?> = _startTime

    private var _endTime = MutableStateFlow<LocalDateTime?>(null)
    val endTime: StateFlow<LocalDateTime?> = _endTime

    private var _reminderTime = MutableStateFlow<LocalDateTime?>(null)
    val reminderTime: StateFlow<LocalDateTime?> = _reminderTime

    private var _diff = MutableStateFlow("")
    val diff: StateFlow<String> = _diff

    private var _files = MutableStateFlow<List<String>>(emptyList())
    val files: StateFlow<List<String>> = _files

    init {
        groupId = savedStateHandle["groupId"]
        taskId = savedStateHandle["taskId"]

        if (groupId != null && taskId != null) {
            viewModelScope.launch {
                taskUseCases.getTask(groupId!!, taskId!!)?.also { task ->
                    _name.value = task.title
                    _desc.value = task.description
                    if (task.startTime != "null") _startTime.value = LocalDateTime.parse(
                        task.startTime,
                        DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")
                    )
                    if (task.endTime != "null") _endTime.value = LocalDateTime.parse(
                        task.endTime,
                        DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")
                    )
//                            if (task.reminderTime != "null") _reminderTime.value = LocalDateTime.parse(task.reminderTime,
//                            DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"))
                    _diff.value = task.priority
                }

                fileUseCases.getFiles(groupId!!, taskId!!).collect {
                    _files.value += it.map { f -> f.path }
                }
            }
        }
    }
}