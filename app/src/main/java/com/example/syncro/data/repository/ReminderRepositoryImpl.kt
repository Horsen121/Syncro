package com.example.syncro.data.repository

import com.example.database.dao.ReminderDao
import com.example.reminders.domain.Reminder
import com.example.syncro.domain.repository.ReminderRepository
import kotlinx.coroutines.flow.Flow

class ReminderRepositoryImpl(
    private val daoLocal: ReminderDao
): ReminderRepository {
    override fun getRemindersByUser(): Flow<List<Reminder>> {
        return daoLocal.getReminders()
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