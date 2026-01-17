package com.example.syncro.presentation.viewmodels.group

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.application.CurrentUser
import com.example.syncro.data.datasourse.remote.RemoteApi
import com.example.syncro.data.datasourse.remote.models.CreateTaskRequest
import com.example.syncro.data.models.File
import com.example.syncro.data.models.Task
import com.example.syncro.domain.usecases.FileUseCases
import com.example.syncro.domain.usecases.TaskUseCases
import com.example.syncro.utils.TaskDifficult
import com.example.syncro.utils.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val tokenManager: TokenManager,
    private val taskUseCases: TaskUseCases,
    private val fileUseCases: FileUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val token = mutableStateOf("")

    private var groupId: Long? = null
    private var taskId: Long? = null

    private var _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private var _desc = MutableStateFlow("")
    val desc: StateFlow<String> = _desc

    private var _startTime = MutableStateFlow<LocalDateTime?>(null)
    val startTime: StateFlow<LocalDateTime?> = _startTime

    private var _endTime = MutableStateFlow<LocalDateTime?>(null)
    val endTime: StateFlow<LocalDateTime?> = _endTime

//    private var _reminderTime = MutableStateFlow<LocalDateTime?>(null)
//    val reminderTime: StateFlow<LocalDateTime?> = _reminderTime

    private var _diff = MutableStateFlow(TaskDifficult.Medium)
    val diff: StateFlow<TaskDifficult> = _diff
    private var _diffOpen = mutableStateOf(false)
    val diffOpen: State<Boolean> = _diffOpen

    private var _files = MutableStateFlow<List<String>>(emptyList())
    val files: StateFlow<List<String>> = _files


    private var _response = MutableStateFlow(false)
    val response: StateFlow<Boolean> = _response

    private var _error = MutableStateFlow("")
    val error: StateFlow<String> = _error

    init {
        viewModelScope.launch {
            token.value = tokenManager.getAccessToken() ?: ""
        }
        groupId = savedStateHandle["groupId"]
        taskId = savedStateHandle["taskId"]

        if (groupId != null && taskId != null) {
            viewModelScope.launch {
                RemoteApi.retrofitService.getTaskById(token.value, groupId!!, taskId!!).let {
                    if (it.isSuccessful) {
                        it.body().let { task ->
                            _name.value = task!!.title
                            _desc.value = task.description
                        }
                    } else {
                        taskUseCases.getTask(groupId!!, taskId!!)?.also { task ->
                            _name.value = task.title
                            _desc.value = task.description
                        }
                    }
                }

                fileUseCases.getFiles(groupId!!, taskId!!).collect {
                    _files.value += it.map { f -> f.path }
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

//    fun onReminderTimeChange(value: LocalDateTime?) {
//        _reminderTime.value = value
//    }

    fun onFileAdd(file: String) {
        _files.value += file
    }

    fun onFileRemove(file: String) {
        _files.value -= file
    }

    fun onSave() {
        viewModelScope.launch {
            val body = CreateTaskRequest(
                title = _name.value,
                description = _desc.value,
                start_time = _startTime.value?.format(DateTimeFormatter.ofPattern("yyyy.MM.DD HH:MM")) ?: "2025.06.15 10:00",
                end_time = _endTime.value?.format(DateTimeFormatter.ofPattern("yyyy.MM.DD HH:MM")) ?: "2025.06.15 10:00",
                priority = _diff.value.name,
                context = ""
            )

            try {
                if (groupId != null && taskId != null) {
                    RemoteApi.retrofitService.updTaskById(token.value, groupId!!, taskId!!, body).let {
                        if (it.isSuccessful) {
                            taskUseCases.addTask(
                                Task(
                                    taskId = taskId,
                                    groupId = groupId!!,
                                    title = _name.value,
                                    description = _desc.value,
                                    createdBy = CurrentUser.id,
                                    startTime = _startTime.value.toString(),
                                    endTime = _endTime.value.toString(),
//                                reminderTime = _reminderTime.value.toString(),
                                    priority = _diff.value.name
                                )
                            ).let { id ->
                                _files.value.forEach { file ->
                                    fileUseCases.addFile(
                                        File(
                                            group_id = groupId!!,
                                            task_id = id!!,
                                            path = file
                                        )
                                    )
                                }
                            }

                            _response.value = true
                        } else {
                            _response.value = true
                            _error.value = it.body()?.toString() ?: "42"
                        }
                    }
                } else if(groupId != null) {
                    RemoteApi.retrofitService.addTaskToGroup(token.value, groupId!!, body).let {
                        if (it.isSuccessful) {
                            runBlocking {
                                it.body().let { task ->
                                    taskUseCases.addTask(
                                        Task(
                                            taskId = task!!.taskId,
                                            groupId = groupId!!,
                                            title = _name.value,
                                            description = _desc.value,
                                            createdBy = CurrentUser.id,
                                            startTime = _startTime.value.toString()
                                                .format("YYYY.MM.DD HH:MM"),
                                            endTime = _endTime.value.toString()
                                                .format("YYYY.MM.DD HH:MM"),
//                                    reminderTime = _reminderTime.value.toString(),
                                            priority = _diff.value.name.lowercase()
                                        )
                                    )
//                                .let { id ->
//                                    _files.value.forEach { file ->
//                                        fileUseCases.addFile(
//                                            File(
//                                                group_id = groupId,
//                                                task_id = id!!,
//                                                path = file
//                                            )
//                                        )
//                                    }
//                                }
                                }
                            }

                            _response.value = true
                        } else {
                            _response.value = true
                            _error.value = it.errorBody()?.string() ?: "42"
                        }
                    }
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "42"
            }
        }
    }

    fun clear() {
        _name.value = ""
        _desc.value = ""
        _diff.value = TaskDifficult.Medium
        _diffOpen.value = false
        _startTime.value = null
        _endTime.value = null
//        _reminderTime.value = null
        _files.value = emptyList()
    }

}