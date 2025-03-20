package com.example.syncro.domain.usecases

import com.example.syncro.domain.usecases.reminder.AddReminder
import com.example.syncro.domain.usecases.reminder.DeleteReminder
import com.example.syncro.domain.usecases.reminder.GetReminder
import com.example.syncro.domain.usecases.reminder.GetReminders

data class ReminderUseCases(
    val getReminders: GetReminders,
    val deleteReminder: DeleteReminder,
    val addReminder: AddReminder,
    val getReminder: GetReminder,
)