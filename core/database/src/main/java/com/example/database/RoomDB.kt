package com.example.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.database.dao.FileDao
import com.example.database.dao.GroupDao
import com.example.database.dao.ReminderDao
import com.example.database.dao.SolutionDao
import com.example.database.dao.SourceFileDao
import com.example.database.dao.TaskDao
import com.example.database.dao.UserDao
import com.example.database.entity.File
import com.example.database.entity.Group
import com.example.database.entity.Solution
import com.example.database.entity.SourceFile
import com.example.database.entity.Task
import com.example.database.entity.User
import com.example.database.entity.Reminder

@Database(
    entities = [
        Group::class,
        Reminder::class,
        Solution::class,
        Task::class,
        File::class,
        SourceFile::class,
        User::class
    ],
    version = 1
)
abstract class SyncroDB : RoomDatabase() {
    abstract fun groupDao(): GroupDao
    abstract fun reminderDao(): ReminderDao
    abstract fun solutionDao(): SolutionDao
    abstract fun taskDao(): TaskDao
    abstract fun fileDao(): FileDao
    abstract fun sourceFileDao(): SourceFileDao
    abstract fun userDao(): UserDao

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