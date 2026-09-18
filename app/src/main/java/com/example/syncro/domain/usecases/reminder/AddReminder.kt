package com.example.syncro.domain.usecases.reminder

import com.example.reminders.domain.Reminder
import com.example.syncro.domain.repository.ReminderRepository

class AddReminder(
    private val repository: ReminderRepository
) {
    @Throws(Exception::class)
    suspend operator fun invoke(item: Reminder): Long? {
        return repository.insertReminder(item)
    }
}