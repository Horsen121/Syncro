package com.example.syncro.domain.repository

import com.example.syncro.data.models.Reminder
import kotlinx.coroutines.flow.Flow

interface ReminderRepository {
    fun getRemindersByUser(): Flow<List<Reminder>>
    suspend fun getReminderById(id: Long): Reminder?
    suspend fun insertReminder(item: Reminder): Long?
    suspend fun deleteReminder(item: Reminder)
}