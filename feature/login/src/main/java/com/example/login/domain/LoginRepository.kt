package com.example.login.domain

import com.example.data.CurrentUser

interface LoginRepository {
    suspend fun login(email: String, password: String): Result<CurrentUser>
}