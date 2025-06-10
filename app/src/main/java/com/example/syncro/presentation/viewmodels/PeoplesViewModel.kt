package com.example.syncro.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.application.CurrentUser
import com.example.syncro.data.datasourse.remote.RemoteApi
import com.example.syncro.data.datasourse.remote.models.AddMemberRequest
import com.example.syncro.data.models.User
import com.example.syncro.domain.usecases.GroupUseCases
import com.example.syncro.domain.usecases.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class PeoplesViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val groupUseCases: GroupUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var groupId = -1L
    private var isAdmin = false
    private var groupName = ""

    private var _users = mutableStateOf<List<User>>(emptyList())
    val users: State<List<User>> = _users

    private var _search = mutableStateOf<List<Pair<String,String>>>(emptyList())
    val search: State<List<Pair<String,String>>> = _search

    init {
        runBlocking {
            savedStateHandle.get<Long?>("groupId").let { id ->
                if (id != null && id != -1L) {
                    groupId = id
                    loadUsers()
                }
            }
            savedStateHandle.get<Boolean>("isAdmin").let {
                if (it != null) {
                    isAdmin = it
                }
            }
            savedStateHandle.get<String>("groupName").let {
                if (it != null) {
                    groupName = it
                }
            }
        }
        viewModelScope.launch {
            RemoteApi.retrofitService.getMembersOfGroup(CurrentUser.token, groupId).let {
                if(it.isSuccessful) {
                    _users.value = it.body()!!
                }
            }
        }
    }

    fun search(user: String) {
        _search.value += Pair(user, "$user.email@mail.ru")
    }

    fun invite(user: String) {
        viewModelScope.launch {
            RemoteApi.retrofitService.addMemberToGroup(CurrentUser.token, groupId, AddMemberRequest(user, false)).let {
                if (it.isSuccessful) {
                    userUseCases.addUser(it.body()!!)

                    val group = groupUseCases.getGroup(groupId)!!
                    groupUseCases.addGroup(
                        group.copy(countPeople = group.countPeople + 1)
                    )
                }
            }
        }
    }

    fun changeIsAdmin(id: Long) {
        viewModelScope.launch {
            _users.value.find { u -> u.user_id == id }?.let { user ->
                if (user.isAdmin) {
                    RemoteApi.retrofitService.addAdminToGroup(CurrentUser.token, groupId, id).let {
                        if (it.isSuccessful) {
                            userUseCases.addUser(
                                user.copy(isAdmin = true)
                            )
                        }
                    }
                } else {
                    RemoteApi.retrofitService.deleteAdminOfGroup(CurrentUser.token, groupId, id).let {
                        if (it.isSuccessful) {
                            userUseCases.addUser(
                                user.copy(isAdmin = false)
                            )
                        }
                    }
                }
            }
        }
    }

    fun getIsAdmin() = isAdmin
    fun getGroupName() = groupName

    fun update() {
        loadUsers()
    }

    private fun loadUsers() {
        userUseCases.getUsers(groupId).onEach {
            _users .value = it
        }.launchIn(viewModelScope)
    }
}