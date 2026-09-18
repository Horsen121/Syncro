package com.example.syncro.domain.usecases.group

import com.example.database.entity.Group
import com.example.syncro.domain.repository.GroupRepository

class DeleteGroup(
    private val repository: GroupRepository
) {
    suspend operator fun invoke(item: Group) {
        repository.deleteGroup(item)
    }
}