package com.example.login.presentation

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

sealed interface LoginUiEvent {
    data object NavigateToHome : LoginUiEvent
    data class ShowToast(val message: String) : LoginUiEvent
}