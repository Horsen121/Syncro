package com.example.syncro.presentation.viewmodels.logreg

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.syncro.application.CurrentUser
import com.example.syncro.data.datasourse.remote.RemoteApi
import com.example.syncro.data.datasourse.remote.models.LoginRequest
import com.example.syncro.data.models.Token
import com.example.syncro.domain.usecases.TokenUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCases: TokenUseCases
) : ViewModel() {

    private var _login = mutableStateOf("")
    val login: State<String> = _login

    private var _password = mutableStateOf("")
    val password: State<String> = _password

    private var _response = mutableStateOf(false)
    val response: State<Boolean> = _response

    private var _error = mutableStateOf("")
    val error: State<String> = _error

    fun onLoginChange(text: String) {
        _login.value = text
    }

    fun onPasswordChange(text: String) {
        _password.value = text
    }

    fun signIn() {
        runBlocking {
            try {
                val body = LoginRequest(_login.value, _password.value)

                RemoteApi.retrofitService.login(body).let {
                    if (it.isSuccessful) {
                        val response = it.body()!!

                        CurrentUser.id = response.user_id
                        CurrentUser.email = _login.value
                        CurrentUser.name = "42" // response.full_name
                        CurrentUser.token = response.access_token

                        useCases.addToken(
                            Token(
                                token = response.access_token,
                                name = CurrentUser.name,
                                email = _login.value,
                                userId = CurrentUser.id
                            )
                        )
                        _response.value = true
                    } else {
                        _error.value = it.body()?.toString() ?: "42"
                    }
                }
            } catch (e: Exception) {
                _response.value = false
                _error.value = e.message ?: "42"
            }
        }
    }

    fun signInWithGoogle() {
        // TODO: go to googleSync screen
    }

    fun passwordChange() {
        // TODO: go to passwordChange screen
    }

}