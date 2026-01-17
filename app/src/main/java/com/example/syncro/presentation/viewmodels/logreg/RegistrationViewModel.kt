package com.example.syncro.presentation.viewmodels.logreg

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.data.datasourse.remote.RemoteApi
import com.example.syncro.data.datasourse.remote.models.RegisterRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
) : ViewModel() {

    private var _name = mutableStateOf("")
    val name: State<String> = _name

    private var _email = mutableStateOf("")
    val email: State<String> = _email

    private var _password1 = mutableStateOf("")
    val password1: State<String> = _password1

    private var _password2 = mutableStateOf("")
    val password2: State<String> = _password2

    private var _agreement = mutableStateOf(false)
    val agreement: State<Boolean> = _agreement

    private var _response = MutableStateFlow(false)
    val response: StateFlow<Boolean> = _response

    fun onNameChange(text: String) {
        _name.value = text
    }
    fun onEmailChange(text: String) {
        _email.value = text
    }
    fun onPassword1Change(text: String) {
        _password1.value = text
    }
    fun onPassword2Change(text: String) {
        _password2.value = text
    }
    fun onAgreementChange() {
        _agreement.value = !_agreement.value
    }
    
    fun registration() {
        viewModelScope.launch {
            try {
                val body = RegisterRequest(_email.value, _password1.value, _name.value)
                RemoteApi.retrofitService.register(body).let {
                    if(it.isSuccessful) _response.value = true
                }
            } catch (e: Exception) {
                _response.value = false
            }
        }
    }
}