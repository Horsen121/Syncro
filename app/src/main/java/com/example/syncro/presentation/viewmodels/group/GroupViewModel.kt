package com.example.syncro.presentation.viewmodels.group

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class GroupViewModel @Inject constructor(
//    private val repository: MyRepository
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