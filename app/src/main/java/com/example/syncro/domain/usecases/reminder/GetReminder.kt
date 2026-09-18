package com.example.syncro.domain.usecases.reminder

import com.example.reminders.domain.Reminder
import com.example.syncro.domain.repository.ReminderRepository

class GetReminder(
    private val repository: ReminderRepository
) {
    suspend operator fun invoke(id: Long): Reminder? {
        return repository.getReminderById(id)
    }
}