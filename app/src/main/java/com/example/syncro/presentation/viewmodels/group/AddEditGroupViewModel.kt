package com.example.syncro.presentation.viewmodels.group

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.data.models.Group
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddEditGroupViewModel @Inject constructor(
//    private val repository: MyRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _name = mutableStateOf("")
    val name: State<String> = _name

    private var _desc = mutableStateOf("")
    val desc: State<String> = _desc

    private var _isPrivate = mutableStateOf(true)
    val isPrivate: State<Boolean> = _isPrivate

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

    fun onNameChange(text: String) {
        _name.value = text
    }

    fun onDescChange(text: String) {
        _desc.value = text
    }

    fun onPrivateChange() {
        _isPrivate.value = !_isPrivate.value
    }

    fun onSave() {

    }

    fun Clear() {
        _name.value = ""
        _desc.value = ""
    }
}