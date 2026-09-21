package com.example.registration.presentation

data class RegistrationUiState(
    val email: String = "",
    val password: String = "",
    val passwordConfirm: String = "",
    val fullName: String = "",
    val agreement: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

sealed interface RegistrationUiEvent {
    data object OnRegistrationSuccess : RegistrationUiEvent
    data class ShowToast(val message: String) : RegistrationUiEvent
}