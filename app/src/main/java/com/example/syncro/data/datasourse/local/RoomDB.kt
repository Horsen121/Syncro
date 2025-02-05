package com.example.syncro.data.datasourse.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.syncro.data.models.Group

@Database(
    entities = [
        Group::class,
               ],
    version = 1
)
abstract class SyncroDB : RoomDatabase() {
//    abstract fun userDao(): UserDao

    companion object {
        private var instance: SyncroDB? = null
        const val DATABASE_NAME = "syncro_database"

        fun getDatabase(context: Context): SyncroDB {
            return instance ?: synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    SyncroDB::class.java,
                    "app_database"
                ).build()
                instance = newInstance
                newInstance
            }
        }
    }
}