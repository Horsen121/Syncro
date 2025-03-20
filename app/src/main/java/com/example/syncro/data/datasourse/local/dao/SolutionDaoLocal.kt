package com.example.syncro.data.datasourse.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.syncro.data.models.Solution
import kotlinx.coroutines.flow.Flow

@Dao
interface SolutionDaoLocal {
    @Query("SELECT * FROM solution WHERE task_id = :id")
    fun getSolutions(id: Long): Flow<List<Solution>>

    @Query("SELECT * FROM solution WHERE solution_id = :id")
    suspend fun getSolutionById(id: Long): Solution?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSolution(item: Solution): Long

    @Delete
    suspend fun deleteSolution(item: Solution)
}