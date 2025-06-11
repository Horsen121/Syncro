package com.example.syncro.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.application.CurrentUser
import com.example.syncro.data.datasourse.remote.RemoteApi
import com.example.syncro.data.models.Group
import com.example.syncro.domain.usecases.GroupUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class GroupsViewModel @Inject constructor(
    private val groupUseCases: GroupUseCases
) : ViewModel() {

    private var _groups = mutableStateOf<List<Group>>(emptyList())
    val groups: State<List<Group>> = _groups

    private var _search = mutableStateOf<List<Pair<String,String>>>(emptyList())
    val search: State<List<Pair<String,String>>> = _search

    init {
        loadGroups()
    }

    fun search(group: String) {
        viewModelScope.launch {
            RemoteApi.retrofitService.findGroup(CurrentUser.token, group).let { response ->
                if(response.isSuccessful) {
                    response.body().let { res ->
                        _search.value = res!!.map { Pair(it.name, it.id.toString()) }
                    }
                }
            }
        }
    }

    fun update() {
        loadGroups()
    }

    private fun loadGroups() {
        runBlocking {
            RemoteApi.retrofitService.getGroupsByUser(CurrentUser.token).let {
                if (it.isSuccessful) {
                    _groups.value = it.body() ?: emptyList()
                    it.body()?.forEach { group ->
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