package com.example.syncro.presentation.viewmodels.group

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.data.models.Task
import com.example.syncro.domain.usecases.TaskUseCases
import com.example.syncro.utils.TaskDifficult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var userId = 1L // TODO: change to
    private var groupId = -1L

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

    private var _diff = mutableStateOf(TaskDifficult.Medium)
    val diff: State<TaskDifficult> = _diff
    private var _diffOpen = mutableStateOf(false)
    val diffOpen: State<Boolean> = _diffOpen

    private var _files = mutableStateOf<List<String>>(emptyList())
    val files: State<List<String>> = _files

    init {
        savedStateHandle.get<Long>("taskId").let { id ->
            if (id != null && id != -1L) {
                viewModelScope.launch {
                    taskUseCases.getTask(id)?.also { task ->
                        _name.value = task.title
                        _desc.value = task.description
                    }
                }
            }
        }
        savedStateHandle.get<Long>("groupId").let { id ->
            if (id != null) groupId = id
        }
    }


    fun onNameChange(text: String) {
        _name.value = text
    }

    fun onDescChange(text: String) {
        _desc.value = text
    }

    fun onDiffChange(newDiff: TaskDifficult) {
        _diff.value = newDiff
    }
    fun onDiffOpenChange(open: Boolean) {
        _diffOpen.value = open
    }

    fun onStartTimeChange(value: LocalDateTime?) {
        _startTime.value = value
    }

    fun onEndTimeChange(value: LocalDateTime?) {
        _endTime.value = value
    }

    fun onReminderTimeChange(value: LocalDateTime?) {
        _reminderTime.value = value
    }

    fun onFileAdd(file: String) {
        _files.value += file
    }

    fun onFileRemove(file: String) {
        _files.value -= file
    }

    fun onSave() {
        viewModelScope.launch {
            taskUseCases.addTask(
                Task(
                    task_id = null,
                    group_id = groupId,
                    title = _name.value,
                    description = _desc.value,
                    created_by = userId,
                    start_time = _startTime.value.toString(),
                    end_time = _endTime.value.toString(),
                    reminderTime = _reminderTime.value.toString(),
                    difficult = _diff.value.name
                )
            )
        }
    }

    fun clear() {
        _name.value = ""
        _desc.value = ""
        _diff.value = TaskDifficult.Medium
        _diffOpen.value = false
        _startTime.value = null
        _endTime.value = null
        _reminderTime.value = null
        _files.value = emptyList()
    }

}