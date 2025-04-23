package com.example.syncro.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.data.models.User
import com.example.syncro.domain.usecases.GroupUseCases
import com.example.syncro.domain.usecases.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

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

    fun add(user: String) { // TODO: remove!!!
        viewModelScope.launch {
            val num = Random.nextInt().toString()
            val addUser = user.split(".")[0]
            userUseCases.addUser(
                User(
                    group_id = groupId,
                    name = addUser,
                    email = "$addUser.$num@mail.ru"
                )
            )
        }
    }

    fun search(user: String) {
        _search.value += Pair(user, "$user.email@mail.ru")
    }

    fun invite(user: String) {
        viewModelScope.launch {
            val group = groupUseCases.getGroup(groupId)!!
            groupUseCases.addGroup(
                group.copy(countPeople = group.countPeople + 1)
            )
        }
        add(user)
    }

    fun changeIsAdmin(id: Long) {
        viewModelScope.launch {
            _users.value.find { u -> u.user_id == id }?.let {
                userUseCases.addUser(
                    it.copy(isAdmin = !it.isAdmin)
                )
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