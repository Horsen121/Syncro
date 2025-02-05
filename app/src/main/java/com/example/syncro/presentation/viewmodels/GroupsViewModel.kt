package com.example.syncro.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.syncro.data.models.Group
import javax.inject.Inject

class GroupsViewModel @Inject constructor(
//    private val repository: MyRepository
) : ViewModel() {

    private var _groups = mutableStateOf<List<Group>>(listOf(Group(1, "Test1"), Group(2, "Test2"), Group(3, "Test3"))) // Group emptyList()
    val groups: State<List<Group>> = _groups
//
//    fun on_varChange(text: String) {
//        _var.value = text
//    }

}