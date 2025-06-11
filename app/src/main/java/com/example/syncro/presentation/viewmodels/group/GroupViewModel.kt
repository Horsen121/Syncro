package com.example.syncro.presentation.viewmodels.group

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.application.CurrentUser
import com.example.syncro.data.datasourse.remote.RemoteApi
import com.example.syncro.data.models.Group
import com.example.syncro.data.models.Task
import com.example.syncro.domain.usecases.GroupUseCases
import com.example.syncro.domain.usecases.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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
    private var groupId = 0L

    private var _currentTopNavBar = mutableStateOf(GroupData.Current)
    val currentTopNavBar: State<GroupData> = _currentTopNavBar

    private var _currentData = mutableStateOf<List<Task>>(emptyList())
    val currentData: State<List<Task>> = _currentData

    private var _group = mutableStateOf<Group?>(null)
    val group: State<Group?> = _group

    private var _isMember = mutableStateOf<Boolean>(false)
    val isMember: State<Boolean> = _isMember

    init {
        savedStateHandle.get<Long>("groupId")?.let {
            if (it != -1L) {
                groupId = it
                runBlocking {
//                viewModelScope.launch {
                    RemoteApi.retrofitService.getGroup(CurrentUser.token, groupId).let { res ->
                        if (res.isSuccessful) {
                            res.body().let { group ->
                                _group.value = group
                                _isMember.value = group!!.is_member
                            }
                        } else {
                            groupUseCases.getGroup(groupId)?.also { group ->
                                _group.value = group
                                if (_group.value != null)
                                    _isMember.value = group.is_member
                            }
                        }
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

    fun join() {
        runBlocking {
            RemoteApi.retrofitService.joinGroup(CurrentUser.token, _group.value!!.group_id!!).let {
                _isMember.value = it
            }
        }
    }

    fun disJoin() {
        runBlocking {
            RemoteApi.retrofitService.disJoinGroup(CurrentUser.token, _group.value!!.group_id!!).let {
                _isMember.value = it

                if (it) {
                    groupUseCases.deleteGroup(group.value!!)
                }
            }
        }
    }

    private fun loadTasks() {
        runBlocking {
            RemoteApi.retrofitService.getTasksByGroup(CurrentUser.token, groupId).also {
                if (it.isSuccessful) {
                    _currentData.value = it.body()!!
                } else {
                    if (_group.value != null) {
                        taskUseCases.getTasks(_group.value!!.group_id!!).onEach { tasks ->
                            _currentData.value = tasks
                        }
                    }
                }
            }
        }
    }
}