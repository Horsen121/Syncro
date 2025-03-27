package com.example.syncro.domain.usecases.user

import com.example.syncro.data.models.User
import com.example.syncro.domain.repository.UserRepository

class DeleteUser(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User) {
        repository.deleteUser(user)
    }
}