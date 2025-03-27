package com.example.syncro.data.datasourse.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.syncro.data.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDaoLocal {
    @Query("SELECT * FROM `user` WHERE group_id = :id")
    fun getUsers(id: Long): Flow<List<User>>

    @Query("SELECT * FROM `user` WHERE user_id = :id")
    suspend fun getUserById(id: Long): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(item: User): Long

    @Delete
    suspend fun deleteUser(item: User)
}