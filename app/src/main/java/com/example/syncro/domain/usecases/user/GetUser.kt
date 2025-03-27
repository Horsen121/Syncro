package com.example.syncro.domain.usecases.user

import com.example.syncro.data.models.User
import com.example.syncro.domain.repository.UserRepository

class GetUser(
    private val repository: UserRepository
) {
    suspend operator fun invoke(id: Long): User? {
        return repository.getUserById(id)
    }
}