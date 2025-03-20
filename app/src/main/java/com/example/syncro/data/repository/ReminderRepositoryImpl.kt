package com.example.syncro.data.repository

import com.example.syncro.data.datasourse.local.dao.ReminderDaoLocal
import com.example.syncro.data.models.Reminder
import com.example.syncro.domain.repository.ReminderRepository
import kotlinx.coroutines.flow.Flow

class ReminderRepositoryImpl(
    private val daoLocal: ReminderDaoLocal
): ReminderRepository {
    override fun getRemindersByUser(id: Long): Flow<List<Reminder>> {
        return daoLocal.getReminders(id)
    }

    override suspend fun getReminderById(id: Long): Reminder? {
        return daoLocal.getReminderById(id)
    }

    override suspend fun insertReminder(item: Reminder): Long {
        return daoLocal.insertReminder(item)
    }

    override suspend fun deleteReminder(item: Reminder) {
        return daoLocal.deleteReminder(item)
    }
}