package com.example.syncro.domain.usecases.reminder

import com.example.syncro.data.models.Reminder
import com.example.syncro.domain.repository.ReminderRepository
import kotlinx.coroutines.flow.Flow

class GetReminders(
    private val repository: ReminderRepository
) {
    operator fun invoke(): Flow<List<Reminder>> {
        return repository.getRemindersByUser()
    }
}