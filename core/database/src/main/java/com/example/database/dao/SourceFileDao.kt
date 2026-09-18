package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.entity.SourceFile
import kotlinx.coroutines.flow.Flow

@Dao
interface SourceFileDao {
    @Query("SELECT * FROM `sourcefile` WHERE groupId = :group AND taskId = :task AND solutionId = :solution")
    fun getSourceFiles(group: Long, task: Long, solution: Long): Flow<List<SourceFile>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSourceFile(item: SourceFile): Long
}