package com.example.syncro.data.datasourse.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.syncro.data.models.SourceFile
import kotlinx.coroutines.flow.Flow

@Dao
interface SourceFileDaoLocal {
    @Query("SELECT * FROM `sourcefile` WHERE group_id = :group AND task_id = :task AND solution_id = :solution")
    fun getSourceFiles(group: Long, task: Long, solution: Long): Flow<List<SourceFile>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSourceFile(item: SourceFile): Long
}