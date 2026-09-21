package com.example.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.login.R
import com.example.login.domain.LoginUseCase
import com.example.utils.strings.StringResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val stringRes: StringResourceProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _events = Channel<LoginUiEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun onEmailChange(text: String) {
        _uiState.update { it.copy(email = text, errorMessage = null) }
    }

    fun onPasswordChange(text: String) {
        _uiState.update { it.copy(password = text, errorMessage = null) }
    }

    fun signIn() {
        val currentState = _uiState.value
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            loginUseCase(currentState.email, currentState.password)
                .onSuccess {
                    _uiState.update { it.copy(isLoading = false) }
                    _events.send(LoginUiEvent.NavigateToHome)
                }
                .onFailure { throwable ->
                    val message = throwable.message ?: stringRes.getString(R.string.login_vm_unknown_error)
                    _uiState.update { it.copy(isLoading = false, errorMessage = message) }
                    _events.send(LoginUiEvent.ShowToast(message))
                }
        }
    }

    fun signInWithGoogle() {
        TODO("Not yet implemented")
    }

    fun passwordChange() {
        TODO("Not yet implemented")
    }
}