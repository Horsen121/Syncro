package com.example.syncro.domain.usecases

import com.example.syncro.domain.usecases.user.AddUser
import com.example.syncro.domain.usecases.user.DeleteUser
import com.example.syncro.domain.usecases.user.GetUser
import com.example.syncro.domain.usecases.user.GetUsers

data class UserUseCases(
    val getUsers: GetUsers,
    val deleteUser: DeleteUser,
    val addUser: AddUser,
    val getUser: GetUser,
)