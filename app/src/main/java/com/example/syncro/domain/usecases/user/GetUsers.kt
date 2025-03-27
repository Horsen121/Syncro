package com.example.syncro.domain.usecases.user

import com.example.syncro.data.models.User
import com.example.syncro.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUsers(
    private val repository: UserRepository
) {
    operator fun invoke(id: Long): Flow<List<User>> {
        return repository.getUsersByGroup(id)
    }
}