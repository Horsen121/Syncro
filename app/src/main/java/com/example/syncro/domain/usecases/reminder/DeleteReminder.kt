package com.example.syncro.domain.usecases.reminder

import com.example.syncro.data.models.Reminder
import com.example.syncro.domain.repository.ReminderRepository

class DeleteReminder(
    private val repository: ReminderRepository
) {
    suspend operator fun invoke(item: Reminder) {
        repository.deleteReminder(item)
    }
}