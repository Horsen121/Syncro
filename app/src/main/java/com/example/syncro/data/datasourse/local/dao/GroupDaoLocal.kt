package com.example.syncro.data.datasourse.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.syncro.data.models.Group
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupDaoLocal {
    @Query("SELECT * FROM `group`")
    fun getGroups(): Flow<List<Group>>

    @Query("SELECT * FROM task WHERE group_id = :id")
    suspend fun getGroupById(id: Long): Group?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroup(item: Group): Long

    @Delete
    suspend fun deleteGroup(item: Group)
}