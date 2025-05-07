package com.example.syncro.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.data.models.Group
import com.example.syncro.domain.usecases.GroupUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

    }

    fun update() {
        loadGroups()
    }

    private fun loadGroups() {
        groupUseCases.getGroups().onEach {
            _groups.value = it
        }.launchIn(viewModelScope)
    }
}