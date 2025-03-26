package com.example.syncro.data.datasourse.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.syncro.data.models.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDaoLocal {
    @Query("SELECT * FROM `task`")
    fun getAllTasks(): Flow<List<Task>>

    @Query("SELECT * FROM `task` WHERE group_id = :id")
    fun getTasks(id: Long): Flow<List<Task>>

    @Query("SELECT * FROM `task` WHERE task_id = :id")
    suspend fun getTaskById(id: Long): Task?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(item: Task): Long

    @Delete
    suspend fun deleteTask(item: Task)
}