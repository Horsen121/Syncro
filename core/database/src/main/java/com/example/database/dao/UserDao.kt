package com.example.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM `user` WHERE groupId = :id")
    fun getUsers(id: Long): Flow<List<User>>

    @Query("SELECT * FROM `user` WHERE userId = :id")
    suspend fun getUserById(id: Long): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(item: User): Long

    @Delete
    suspend fun deleteUser(item: User)
}