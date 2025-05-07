package com.example.syncro.utils

sealed interface UiState {
    object Success : UiState
    object  Loading : UiState
    object  Error : UiState
    object  Null : UiState
}