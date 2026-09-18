package com.example.syncro.data.repository

import com.example.database.dao.UserDao
import com.example.database.entity.User
import com.example.syncro.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val daoLocal: UserDao
): UserRepository {
    override fun getUsersByGroup(id: Long): Flow<List<User>> {
        return daoLocal.getUsers(id)
    }

    override suspend fun getUserById(id: Long): User? {
        return daoLocal.getUserById(id)
    }

    override suspend fun insertUser(item: User): Long {
        return daoLocal.insertUser(item)
    }

    override suspend fun deleteUser(item: User) {
        return daoLocal.deleteUser(item)
    }
}