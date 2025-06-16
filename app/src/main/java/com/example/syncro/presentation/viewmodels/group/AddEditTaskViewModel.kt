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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    private val fileUseCases: FileUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var groupId = -1L
    private var taskId: Long? = null

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


    private var _response = mutableStateOf(false)
    val response: State<Boolean> = _response

    private var _error = mutableStateOf("")
    val error: State<String> = _error

    init {
        runBlocking {
            savedStateHandle.get<Long>("groupId").let { id ->
                if (id != null) groupId = id
            }
            savedStateHandle.get<Long>("taskId").let { id ->
                if (id != null && id != -1L) taskId = id
            }
        }

        if (taskId != null) {
            viewModelScope.launch {
                RemoteApi.retrofitService.getTaskById(CurrentUser.token, groupId, taskId!!).let {
                    if (it.isSuccessful) {
                        it.body().let { task ->
                            _name.value = task!!.title
                            _desc.value = task.description
                        }
                    } else {
                        taskUseCases.getTask(groupId, taskId!!)?.also { task ->
                            _name.value = task.title
                            _desc.value = task.description
                        }
                    }
                }

                fileUseCases.getFiles(groupId, taskId!!).collect {
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
        runBlocking {
            val body = CreateTaskRequest(
                title = _name.value,
                description = _desc.value,
                start_time = "2025.06.15 10:00", //_startTime.value?.format(DateTimeFormatter.ofPattern("YYYY.MM.DD HH:MM")) ?: "2025.06.15 10:00",
                end_time = "2025.06.16 10:00", // _endTime.value?.format(DateTimeFormatter.ofPattern("YYYY.MM.DD HH:MM")) ?: "2025.06.15 10:00",
                priority = "Средний", //_diff.value.name,
                context = ""
            )

            try {
                if (taskId != null) {
                    RemoteApi.retrofitService.updTaskById(CurrentUser.token, groupId, body).let {
                        if (it.isSuccessful) {
                            taskUseCases.addTask(
                                Task(
                                    task_id = taskId,
                                    group_id = groupId,
                                    title = _name.value,
                                    description = _desc.value,
                                    created_by = CurrentUser.id,
                                    start_time = _startTime.value.toString(),
                                    end_time = _endTime.value.toString(),
//                                reminderTime = _reminderTime.value.toString(),
                                    priority = _diff.value.name
                                )
                            ).let { id ->
                                _files.value.forEach { file ->
                                    fileUseCases.addFile(
                                        File(
                                            group_id = groupId,
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
                } else {
                    RemoteApi.retrofitService.addTaskToGroup(CurrentUser.token, groupId, body).let {
                        if (it.isSuccessful) {
                            runBlocking {
                                it.body().let { task ->
                                    taskUseCases.addTask(
                                        Task(
                                            task_id = task!!.task_id,
                                            group_id = groupId,
                                            title = _name.value,
                                            description = _desc.value,
                                            created_by = CurrentUser.id,
                                            start_time = _startTime.value.toString()
                                                .format("YYYY.MM.DD HH:MM"),
                                            end_time = _endTime.value.toString()
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
        _reminderTime.value = null
        _files.value = emptyList()
    }

}