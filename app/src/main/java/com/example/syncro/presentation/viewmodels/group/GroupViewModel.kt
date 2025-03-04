package com.example.syncro.presentation.viewmodels.group

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.data.models.Group
import kotlinx.coroutines.launch
import javax.inject.Inject

class GroupViewModel @Inject constructor(
//    private val repository: GroupRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    enum class GroupData {
        Current,
        Plan,
        Done
    }


    private var _currentTopNavBar = mutableStateOf(GroupData.Current)
    val currentTopNavBar: State<GroupData> = _currentTopNavBar

    private var _currentData = mutableStateOf<List<String>>(emptyList()) // TODO change to relevant element
    val currentData: State<List<String>> = _currentData

    private var _group = mutableStateOf<Group?>(null)
    val group: State<Group?> = _group
    init {
        savedStateHandle.get<Long>("groupId")?.let { groupId ->
            if (groupId != -1L) {
                viewModelScope.launch {
//                    groupUseCases.getGroup(groupId)?.also { group ->
//                        _group.value = group
//                    }
                }
            }
        }
    }

    fun getData(type: GroupData) { // TODO: get data from server / DB 
        viewModelScope.launch {
            when(type) {
                GroupData.Current -> {
                    _currentTopNavBar.value = GroupData.Current
                }
                GroupData.Plan -> {
                    _currentTopNavBar.value = GroupData.Plan
                }
                GroupData.Done -> {
                    _currentTopNavBar.value = GroupData.Done
                }
            }
        }
    }

}