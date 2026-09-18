package com.example.syncro.domain.usecases.group

import com.example.database.entity.Group
import com.example.syncro.domain.repository.GroupRepository

class AddGroup(
    private val repository: GroupRepository
) {
    @Throws(Exception::class)
    suspend operator fun invoke(item: Group): Long? {
        return repository.insertGroup(item)
    }
}