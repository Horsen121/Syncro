package com.example.syncro.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.data.models.Reminder
import com.example.syncro.data.models.Task
import com.example.syncro.domain.usecases.ReminderUseCases
import com.example.syncro.domain.usecases.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RemindersViewModel @Inject constructor(
    private val reminderUseCases: ReminderUseCases,
    private val taskUseCases: TaskUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var groupId = -1L

    private var _reminders = MutableStateFlow<List<Pair<Reminder, String>>>(emptyList())
    val reminders: StateFlow<List<Pair<Reminder, String>>> = _reminders

    init {
        savedStateHandle.get<Long?>("groupId").let { id ->
            if (id != null && id != -1L) {
                groupId = id
                loadGroups(id)
            }
        }
    }

    fun update() {
        loadGroups(groupId)
    }

    private fun loadGroups(groupId: Long) {
        var reminders: List<Reminder> = emptyList()
        var tasks: List<Task> = emptyList()

        reminderUseCases.getReminders().onEach {
            reminders = if (groupId != -1L) {
                it.filter { r -> r.group_id == groupId }
            } else it
        }.launchIn(viewModelScope)
        taskUseCases.getAllTasks().onEach {
            tasks = it
        }.launchIn(viewModelScope)

        _reminders.value = reminders.map { r -> Pair(r, tasks.filter { t -> t.taskId == r.task_id }[0].title)}
    }
}