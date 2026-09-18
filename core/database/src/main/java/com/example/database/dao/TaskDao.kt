package com.example.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.entity.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM `task`")
    fun getAllTasks(): Flow<List<Task>>

    @Query("SELECT * FROM `task` WHERE groupId = :id")
    fun getTasks(id: Long): Flow<List<Task>>

    @Query("SELECT * FROM `task` WHERE groupId = :groupId AND taskId = :taskId")
    suspend fun getTaskById(groupId: Long, taskId: Long): Task?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(item: Task): Long

    @Delete
    suspend fun deleteTask(item: Task)
}