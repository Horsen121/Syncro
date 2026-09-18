package com.example.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.entity.Solution
import kotlinx.coroutines.flow.Flow

@Dao
interface SolutionDao {
    @Query("SELECT * FROM `solution` WHERE task_id = :id")
    fun getSolutions(id: Long): Flow<List<Solution>>

    @Query("SELECT * FROM `solution` WHERE group_id = :groupId AND task_id = :taskId AND solution_id = :solId")
    suspend fun getSolutionById(groupId: Long, taskId: Long, solId: Long): Solution?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSolution(item: Solution): Long

    @Delete
    suspend fun deleteSolution(item: Solution)
}