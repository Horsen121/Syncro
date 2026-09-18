package com.example.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.entity.Group
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupDao {
    @Query("SELECT * FROM `group`")
    fun getGroups(): Flow<List<Group>>

    @Query("SELECT * FROM `group` WHERE group_id = :id")
    suspend fun getGroupById(id: Long): Group?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroup(item: Group): Long

    @Delete
    suspend fun deleteGroup(item: Group)
}