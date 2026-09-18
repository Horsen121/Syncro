package com.example.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.entity.Reminder
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {
    @Query("SELECT * FROM `reminder`")
    fun getReminders(): Flow<List<Reminder>>

    @Query("SELECT * FROM `reminder` WHERE reminderId = :id")
    suspend fun getReminderById(id: Long): Reminder?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(item: Reminder): Long

    @Delete
    suspend fun deleteReminder(item: Reminder)
}