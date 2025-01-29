package com.example.syncro.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(
//    private val repository: MyRepository
) : ViewModel() {
    // ...
    private var _login = mutableStateOf("")
    val login: State<String> = _login

    private var _password = mutableStateOf("")
    val password: State<String> = _password

    fun onLoginChange(text: String) {
        _login.value = text
    }

    fun onPasswordChange(text: String) {
        _password.value = text
    }

    fun signIn() {

    }

}