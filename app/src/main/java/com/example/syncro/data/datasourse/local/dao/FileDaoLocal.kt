package com.example.syncro.data.datasourse.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.syncro.data.models.File
import kotlinx.coroutines.flow.Flow

@Dao
interface FileDaoLocal {
    @Query("SELECT * FROM `file` WHERE group_id = :group AND task_id = :task")
    fun getFiles(group: Long, task: Long): Flow<List<File>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFile(item: File): Long
}