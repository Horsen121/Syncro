package com.example.syncro.data.datasourse.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.syncro.data.datasourse.local.dao.FileDaoLocal
import com.example.syncro.data.datasourse.local.dao.GroupDaoLocal
import com.example.syncro.data.datasourse.local.dao.ReminderDaoLocal
import com.example.syncro.data.datasourse.local.dao.SolutionDaoLocal
import com.example.syncro.data.datasourse.local.dao.SourceFileDaoLocal
import com.example.syncro.data.datasourse.local.dao.TaskDaoLocal
import com.example.syncro.data.datasourse.local.dao.UserDaoLocal
import com.example.syncro.data.models.File
import com.example.syncro.data.models.Group
import com.example.syncro.data.models.Reminder
import com.example.syncro.data.models.Solution
import com.example.syncro.data.models.SourceFile
import com.example.syncro.data.models.Task
import com.example.syncro.data.models.User

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
    abstract fun groupDao(): GroupDaoLocal
    abstract fun reminderDao(): ReminderDaoLocal
    abstract fun solutionDao(): SolutionDaoLocal
    abstract fun taskDao(): TaskDaoLocal
    abstract fun fileDao(): FileDaoLocal
    abstract fun sourceFileDao(): SourceFileDaoLocal
    abstract fun userDao(): UserDaoLocal

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