package com.example.syncro.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.data.datasourse.remote.RemoteApi
import com.example.syncro.data.datasourse.remote.models.AddMemberRequest
import com.example.syncro.data.models.User
import com.example.syncro.domain.usecases.GroupUseCases
import com.example.syncro.domain.usecases.UserUseCases
import com.example.syncro.utils.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeoplesViewModel @Inject constructor(
    private val tokenManager: TokenManager,
    private val userUseCases: UserUseCases,
    private val groupUseCases: GroupUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val token = mutableStateOf("")

    private var groupId = -1L
    private var isAdmin = false
    private var groupName = ""

    private var _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    private var _search = MutableStateFlow("")
    val search: StateFlow<String> = _search

    init {
        viewModelScope.launch {
            token.value = tokenManager.getAccessToken() ?: ""

            savedStateHandle.get<Long?>("groupId").let { id ->
                if (id != null && id != -1L) {
                    groupId = id
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
        }.let {
            loadUsers()
        }

    }

    fun search(user: String) {
        _search.value = user

//        runBlocking {
////            RemoteApi.retrofitService
//        }
    }

    fun invite(user: String) {
        viewModelScope.launch {
            RemoteApi.retrofitService.addMemberToGroup(token.value, groupId, AddMemberRequest(user, false)).let {
                if (it.isSuccessful) {
//                    userUseCases.addUser(it.body()!!)

                    val group = groupUseCases.getGroup(groupId)!!
                    groupUseCases.addGroup(
                        group.copy(members_count = group.members_count + 1)
                    )
                }
            }
        }
    }

    fun changeIsAdmin(id: Long) {
        viewModelScope.launch {
            _users.value.find { u -> u.user_id == id }?.let { user ->
                if (user.is_admin) {
                    RemoteApi.retrofitService.addAdminToGroup(token.value, groupId, id).let {
                        if (it.isSuccessful) {
                            userUseCases.addUser(
                                user.copy(is_admin = true)
                            )
                        }
                    }
                } else {
                    RemoteApi.retrofitService.deleteAdminOfGroup(token.value, groupId, id).let {
                        if (it.isSuccessful) {
                            userUseCases.addUser(
                                user.copy(is_admin = false)
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
        viewModelScope.launch {
            RemoteApi.retrofitService.getMembersOfGroup(token.value, groupId).let {
                if(it.isSuccessful) {
                    _users.value = it.body() ?: emptyList()
                } else {
                    userUseCases.getUsers(groupId).onEach { user ->
                        _users.value = user
                    }
                }
            }
        }
    }
}