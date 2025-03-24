package com.example.syncro.presentation.viewmodels.group

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.data.models.Group
import com.example.syncro.data.models.Task
import com.example.syncro.domain.usecases.GroupUseCases
import com.example.syncro.domain.usecases.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(
    private val groupUseCases: GroupUseCases,
    private val taskUseCases: TaskUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    enum class GroupData {
        Current,
        Plan,
        Done
    }

    private var _currentTopNavBar = mutableStateOf(GroupData.Current)
    val currentTopNavBar: State<GroupData> = _currentTopNavBar

    private var _currentData = mutableStateOf<List<Task>>(emptyList())
    val currentData: State<List<Task>> = _currentData

    private var _group = mutableStateOf<Group?>(null)
    val group: State<Group?> = _group
    init {
        savedStateHandle.get<Long>("groupId")?.let { groupId ->
            if (groupId != -1L) {
                viewModelScope.launch {
                    groupUseCases.getGroup(groupId)?.also { group ->
                        _group.value = group
                        loadTasks()
                    }
                }
            }
        }
    }

    fun getData(type: GroupData) { // TODO: get data from server / DB
        when (type) {
            GroupData.Current -> { // TODO: add more options in dao and repositories for getting data
                loadTasks().also {
                    _currentTopNavBar.value = GroupData.Current
                }
            }

            GroupData.Plan -> {
                loadTasks().also {
                    _currentTopNavBar.value = GroupData.Plan
                }
            }

            GroupData.Done -> {
                loadTasks().also {
                    _currentTopNavBar.value = GroupData.Done
                }
            }
        }
    }

    private fun loadTasks() {
        taskUseCases.getTasks(_group.value?.group_id!!).onEach {
            _currentData.value = it
        }.launchIn(viewModelScope)
    }
}