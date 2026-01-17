package com.example.syncro.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.data.datasourse.remote.RemoteApi
import com.example.syncro.data.models.Group
import com.example.syncro.domain.usecases.GroupUseCases
import com.example.syncro.utils.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupsViewModel @Inject constructor(
    private val tokenManager: TokenManager,
    private val groupUseCases: GroupUseCases
) : ViewModel() {
    private val token = mutableStateOf("")

    private var _groups = MutableStateFlow<List<Group>>(emptyList())
    val groups: StateFlow<List<Group>> = _groups

    private var _search = MutableStateFlow<List<Pair<String,String>>>(emptyList())
    val search: StateFlow<List<Pair<String,String>>> = _search

    init {
        viewModelScope.launch {
            token.value = tokenManager.getAccessToken() ?: ""
        }.let {
            loadGroups()
        }
    }

    fun search(group: String) {
        viewModelScope.launch {
            RemoteApi.retrofitService.findGroup(token.value, group).let { response ->
                if(response.isSuccessful) {
                    response.body().let { res ->
                        _search.value = res!!.map { Pair(it.name, it.groupId.toString()) }
                    }
                }
            }
        }
    }

    fun clear() {
        _search.value = emptyList()
    }

    fun update() {
        loadGroups()
    }

    private fun loadGroups() {
        viewModelScope.launch {
            RemoteApi.retrofitService.getGroupsByUser(token.value).let {
                if (it.isSuccessful) {
                    _groups.value = it.body() ?: emptyList()
                    _groups.value.forEach { group ->
                        groupUseCases.addGroup(group)
                    }
                } else {
                    groupUseCases.getGroups().onEach { groups ->
                        _groups.value = groups
                    }.launchIn(viewModelScope)
                }
            }
        }
    }
}