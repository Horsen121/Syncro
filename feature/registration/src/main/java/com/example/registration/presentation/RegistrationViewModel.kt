package com.example.registration.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registration.R
import com.example.registration.domain.RegistrationUseCase
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
class RegistrationViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase,
    private val stringRes: StringResourceProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegistrationUiState())
    val uiState: StateFlow<RegistrationUiState> = _uiState.asStateFlow()

    private val _events = Channel<RegistrationUiEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()


    fun onNameChange(text: String) {
        _uiState.update { it.copy(fullName = text, errorMessage = null) }
    }
    fun onEmailChange(text: String) {
        _uiState.update { it.copy(email = text, errorMessage = null) }
    }
    fun onPassword1Change(text: String) {
        _uiState.update { it.copy(password = text, errorMessage = null) }
    }
    fun onPassword2Change(text: String) {
        _uiState.update { it.copy(passwordConfirm = text, errorMessage = null) }
    }
    fun onAgreementChange() {
        _uiState.update { it.copy(agreement = !_uiState.value.agreement, errorMessage = null) }
    }
    
    fun registration() {
        val currentState = _uiState.value
        viewModelScope.launch {
            if (currentState.password == currentState.passwordConfirm) {
                _uiState.update { it.copy(isLoading = true, errorMessage = null) }

                registrationUseCase(
                    currentState.email,
                    currentState.password,
                    currentState.fullName
                )
                    .onSuccess {
                        _uiState.update { it.copy(isLoading = false) }
                        _events.send(RegistrationUiEvent.OnRegistrationSuccess)
                    }
                    .onFailure { throwable ->
                        val message =
                            throwable.message ?: stringRes.getString(R.string.reg_vm_unknown_error)
                        _uiState.update { it.copy(isLoading = false, errorMessage = message) }
                        _events.send(RegistrationUiEvent.ShowToast(message))
                    }
            } else {
                val message = stringRes.getString(R.string.reg_vm_password_confirm_error)
                _events.send(RegistrationUiEvent.ShowToast(message))
            }
        }
    }
}