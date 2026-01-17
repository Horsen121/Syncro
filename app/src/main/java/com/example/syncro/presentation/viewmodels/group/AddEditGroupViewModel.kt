package com.example.syncro.presentation.viewmodels.group

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.application.CurrentUser
import com.example.syncro.data.datasourse.remote.RemoteApi
import com.example.syncro.data.datasourse.remote.models.CreateGroupRequest
import com.example.syncro.data.models.Group
import com.example.syncro.domain.usecases.GroupUseCases
import com.example.syncro.utils.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class AddEditGroupViewModel @Inject constructor(
    private val tokenManager: TokenManager,
    private val groupUseCases: GroupUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val token = mutableStateOf("")

    private var groupId: Long? = null
    private var countPeople = 1

    private var _response = MutableStateFlow(false)
    val response: StateFlow<Boolean> = _response

    private var _error = MutableStateFlow("")
    val error: StateFlow<String> = _error

    private var _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private var _desc = MutableStateFlow("")
    val desc: StateFlow<String> = _desc

    private var _isPrivate = mutableStateOf(true)
//    val isPrivate: State<Boolean> = _isPrivate

    private var _isAdmin= mutableStateOf(true)
//    val isAdmin: State<Boolean> = _isAdmin

    init {
        viewModelScope.launch {
            token.value = tokenManager.getAccessToken() ?: ""
        }
        groupId = savedStateHandle["groupId"]

        if (groupId != null) {
            viewModelScope.launch {
                RemoteApi.retrofitService.getGroup(token.value, groupId!!).let {
                    if (it.isSuccessful) {
                        it.body().let { group ->
                            _name.value = group!!.name
                            _desc.value = group.description
//                        _isPrivate.value = group.isPrivate
                            _isAdmin.value = group.is_admin
                            countPeople = group.members_count
                        }
                    } else {
                        groupUseCases.getGroup(groupId!!)?.also { group ->
                            _name.value = group.name
                            _desc.value = group.description
//                        _isPrivate.value = group.isPrivate
                            _isAdmin.value = group.is_admin
                            countPeople = group.members_count
                        }
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

//    fun onPrivateChange() {
//        _isPrivate.value = !_isPrivate.value
//    }
//
//    fun onAdminChange() {
//        _isAdmin.value = !_isAdmin.value
//    }

    fun onSave() {
        viewModelScope.launch {
            val body = CreateGroupRequest(_name.value, _desc.value)

            try {
                if (groupId != null) {
                    RemoteApi.retrofitService.updGroupById(token.value, groupId!!, body).let {
                        if (it.isSuccessful) {
                            groupUseCases.addGroup(it.body()!!)

                            _response.value = true
                        } else {
                            _response.value = false
                        }
                    }
                } else {
                    RemoteApi.retrofitService.createGroup(token.value, body).let {
                        if (it.isSuccessful) {
                            val response = it.body()!!

                            runBlocking {
                                groupUseCases.addGroup(
                                    Group(
                                        group_id = response.groupId,
                                        name = response.name,
                                        description = response.description,
                                        is_admin = response.isAdmin,
                                        created_by = CurrentUser.id,
                                        members_count = 1
                                    )
                                )
                            }

                            _response.value = true
                        } else {
                            _response.value = false
                        }
                    }
                }
            } catch (e: Exception) {
                _response.value = false
                _error.value = e.message ?: "42"
            }
        }
    }

    fun clear() {
        _name.value = ""
        _desc.value = ""
        _isPrivate.value = false
        _isAdmin.value = false
    }
}