package com.example.syncro.presentation.viewmodels.group

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.application.CurrentUser
import com.example.syncro.data.models.Group
import com.example.syncro.domain.usecases.GroupUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditGroupViewModel @Inject constructor(
    private val groupUseCases: GroupUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var groupId: Long? = null
    private var countPeople = 1

    private var _name = mutableStateOf("")
    val name: State<String> = _name

    private var _desc = mutableStateOf("")
    val desc: State<String> = _desc

    private var _isPrivate = mutableStateOf(true)
    val isPrivate: State<Boolean> = _isPrivate

    private var _isAdmin= mutableStateOf(true)
    val isAdmin: State<Boolean> = _isAdmin

    init {
        savedStateHandle.get<Long>("groupId")?.let { groupId ->
            if (groupId != -1L) {
                this.groupId = groupId
                viewModelScope.launch {
                    groupUseCases.getGroup(groupId)?.also { group ->
                        _name.value = group.name
                        _desc.value = group.description
//                        _isPrivate.value = group.isPrivate
                        _isAdmin.value = group.isAdmin
                        countPeople = group.countPeople
                    }
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

    fun onAdminChange() {
        _isAdmin.value = !_isAdmin.value
    }

    fun onSave() {
        viewModelScope.launch {
            groupUseCases.addGroup(
                Group(
                    group_id = groupId,
                    name = _name.value,
                    description = _desc.value,
                    isAdmin = _isAdmin.value,
                    created_by = CurrentUser.id,
                    countPeople = countPeople
                )
            )
        }
    }

    fun clear() {
        _name.value = ""
        _desc.value = ""
        _isPrivate.value = false
        _isAdmin.value = false
    }
}