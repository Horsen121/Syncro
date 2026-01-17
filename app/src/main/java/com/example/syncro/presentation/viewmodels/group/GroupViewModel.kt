package com.example.syncro.presentation.viewmodels.group

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.application.CurrentUser
import com.example.syncro.data.datasourse.remote.RemoteApi
import com.example.syncro.data.datasourse.remote.models.JoinGroupRequest
import com.example.syncro.data.models.Group
import com.example.syncro.data.models.Task
import com.example.syncro.domain.usecases.GroupUseCases
import com.example.syncro.domain.usecases.TaskUseCases
import com.example.syncro.utils.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(
    private val tokenManager: TokenManager,
    private val groupUseCases: GroupUseCases,
    private val taskUseCases: TaskUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val token = mutableStateOf("")

    enum class GroupData {
        Current,
        Plan,
        Done
    }
    private var groupId: Long? = null

    private var _currentTopNavBar = MutableStateFlow(GroupData.Current)
    val currentTopNavBar: StateFlow<GroupData> = _currentTopNavBar

    private var _currentData = MutableStateFlow<List<Task>>(emptyList())
    val currentData: StateFlow<List<Task>> = _currentData

    private var _group = MutableStateFlow<Group?>(null)
    val group: StateFlow<Group?> = _group

    private var _isMember = MutableStateFlow(false)
    val isMember: StateFlow<Boolean> = _isMember

    private var _response = MutableStateFlow(false)
    val response: StateFlow<Boolean> = _response

    init {
        groupId = savedStateHandle["groupId"]
        if (groupId != null) {
            viewModelScope.launch {
                RemoteApi.retrofitService.getGroup(token.value, groupId!!).let { res ->
                    if (res.isSuccessful) {
                        res.body().let { group ->
                            _group.value = group
                            _isMember.value = group!!.is_member
                        }
                    } else {
                        groupUseCases.getGroup(groupId!!)?.also { group ->
                            _group.value = group
                            if (_group.value != null)
                                _isMember.value = group.is_member
                        }
                    }
                    loadTasks()
                }
            }
        } else {

        }
    }

    fun getData(type: GroupData) {
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
        viewModelScope.launch {
            val body = JoinGroupRequest(CurrentUser.email, CurrentUser.name)

            RemoteApi.retrofitService.joinGroup(token.value, groupId!!, body).let {
                if(it.isSuccessful)
                    _isMember.value = true
            }
        }
    }

    fun disJoin() {
        viewModelScope.launch {
            RemoteApi.retrofitService.disJoinGroup(token.value, _group.value!!.group_id!!).let {
                if (it.isSuccessful) {
                    groupUseCases.deleteGroup(group.value!!)

                    _response.value = true
                }
            }
        }
    }

    fun update() = loadTasks()

    private fun loadTasks() {
        viewModelScope.launch {
            RemoteApi.retrofitService.getTasksByGroup(token.value, groupId!!).also {
                if (it.isSuccessful) {
                    _currentData.value = it.body() ?: emptyList()
                    _currentData.value.forEach { task ->
                        taskUseCases.addTask(task)
                    }
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