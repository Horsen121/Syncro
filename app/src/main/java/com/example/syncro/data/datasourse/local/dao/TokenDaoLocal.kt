package com.example.syncro.data.datasourse.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.syncro.data.models.Token

@Dao
interface TokenDaoLocal {
    @Query("SELECT * FROM `token` WHERE id = 1")
    suspend fun getToken(): Token

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToken(item: Token): Long
}