package com.example.syncro.data.repository

import com.example.syncro.data.datasourse.local.dao.GroupDaoLocal
import com.example.syncro.data.models.Group
import com.example.syncro.domain.repository.GroupRepository
import kotlinx.coroutines.flow.Flow

class GroupRepositoryImpl(
    private val daoLocal: GroupDaoLocal
): GroupRepository {
    override fun getGroups(): Flow<List<Group>> {
        return daoLocal.getGroups()
    }

    override suspend fun getGroupById(id: Long): Group? {
        return daoLocal.getGroupById(id)
    }

    override suspend fun insertGroup(item: Group): Long {
        return daoLocal.insertGroup(item)
    }

    override suspend fun deleteGroup(item: Group) {
        return daoLocal.deleteGroup(item)
    }
}