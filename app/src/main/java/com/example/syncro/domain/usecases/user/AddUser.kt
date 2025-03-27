package com.example.syncro.domain.usecases.user

import com.example.syncro.data.models.User
import com.example.syncro.domain.repository.UserRepository

class AddUser(
    private val repository: UserRepository
) {
    @Throws(Exception::class)
    suspend operator fun invoke(user: User): Long? {
        return repository.insertUser(user)
    }
}