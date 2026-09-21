package com.example.registration.domain

import com.example.data.CurrentUser

interface RegistrationRepository {
    suspend fun register(email: String, password: String, fullName: String): Result<CurrentUser>
}