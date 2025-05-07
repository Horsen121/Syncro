package com.example.syncro.presentation.viewmodels.logreg

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncro.application.CurrentUser
import com.example.syncro.data.datasourse.remote.RemoteApi
import com.example.syncro.data.models.Token
import com.example.syncro.domain.usecases.TokenUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCases: TokenUseCases
) : ViewModel() {

    private var _login = mutableStateOf("")
    val login: State<String> = _login

    private var _password = mutableStateOf("")
    val password: State<String> = _password

    private var _response = mutableStateOf("")
    val response: State<String> = _response

    fun onLoginChange(text: String) {
        _login.value = text
    }

    fun onPasswordChange(text: String) {
        _password.value = text
    }

    fun signIn() { // Boolean
        viewModelScope.launch {
            try {
//                val body = JSONObject(mapOf("email" to _login.value, "password" to _password.value).toString()) //LoginBody(_login.value, _password.value)
//                _response.value = body.toString()

                RemoteApi.retrofitService.login(_login.value, _password.value).let { // body.toString()
                    // TODO: convert response to data
                    CurrentUser.id = 1L
                    CurrentUser.email = "user1@example.com"
                    CurrentUser.name = "Test User 1"

                    useCases.addToken(
                        Token(
                            token = "",
                            name = CurrentUser.name,
                            email = _login.value,
                            userId = CurrentUser.id
                        )
                    )
                    _response.value = it //.await() ?: "42.1"
                }
            } catch (e: Exception) {
                _response.value = e.message ?: "42.2"
            }
        }

//        RemoteApi.retrofitService.login(_login.value, _password.value).enqueue(object: Callback<String> {
//            override fun onResponse(p0: Call<String>, p1: Response<String>) {
//                if (p1.isSuccessful) {
//                    p1.body()?.let { _response.value = it }
//
//                    CurrentUser.id = 1L
//                    CurrentUser.email = "user1@example.com"
//                    CurrentUser.name = "Test User 1"
//
//                    viewModelScope.launch {
//                        useCases.addToken(
//                            Token(
//                                token = "",
//                                name = CurrentUser.name,
//                                email = _login.value,
//                                userId = CurrentUser.id
//                            )
//                        )
//                    }
//                }
//            }
//
//            override fun onFailure(p0: Call<String>, p1: Throwable) {
//                _response.value = p1.message ?: "42"
//            }
//        })
    }

    fun signInWithGoogle() {
        // TODO: go to googleSync screen
    }

    fun passwordChange() {
        // TODO: go to passwordChange screen
    }

}