package com.example.syncro.application

sealed class Routing(val route: String) {
    data object LoginScreen: Routing("login_screen")
    data object RegistrationScreen: Routing("registration_screen")
}