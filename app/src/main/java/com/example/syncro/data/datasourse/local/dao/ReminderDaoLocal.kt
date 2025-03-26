package com.example.syncro.data.datasourse.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.syncro.data.models.Reminder
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDaoLocal {
    @Query("SELECT * FROM `reminder`")
    fun getReminders(): Flow<List<Reminder>>

    @Query("SELECT * FROM `reminder` WHERE reminder_id = :id")
    suspend fun getReminderById(id: Long): Reminder?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(item: Reminder): Long

    @Delete
    suspend fun deleteReminder(item: Reminder)
}