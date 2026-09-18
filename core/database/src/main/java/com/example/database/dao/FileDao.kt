package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.entity.File
import kotlinx.coroutines.flow.Flow

@Dao
interface FileDao {
    @Query("SELECT * FROM `file` WHERE groupId = :group AND taskId = :task")
    fun getFiles(group: Long, task: Long): Flow<List<File>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFile(item: File): Long
}