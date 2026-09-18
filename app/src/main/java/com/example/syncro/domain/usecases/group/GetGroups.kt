package com.example.syncro.domain.usecases.group

import com.example.database.entity.Group
import com.example.syncro.domain.repository.GroupRepository
import kotlinx.coroutines.flow.Flow

class GetGroups(
    private val repository: GroupRepository
) {
    operator fun invoke(): Flow<List<Group>> {
        return repository.getGroups()
    }
}