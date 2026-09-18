package com.example.syncro.domain.usecases.group

import com.example.database.entity.Group
import com.example.syncro.domain.repository.GroupRepository

class GetGroup(
    private val repository: GroupRepository
) {
    suspend operator fun invoke(id: Long): Group? {
        return repository.getGroupById(id)
    }
}