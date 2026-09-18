package com.example.database

import android.content.Context
import androidx.room.Room
import com.example.database.dao.FileDao
import com.example.database.dao.GroupDao
import com.example.database.dao.ReminderDao
import com.example.database.dao.SolutionDao
import com.example.database.dao.SourceFileDao
import com.example.database.dao.TaskDao
import com.example.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideSyncroDB(
        @ApplicationContext context: Context
    ): SyncroDB {
        return Room.databaseBuilder(
                context,
                SyncroDB::class.java,
                SyncroDB.DATABASE_NAME
            )
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    @Singleton
    fun provideFileDao(database: SyncroDB): FileDao =
        database.fileDao()

    @Provides
    @Singleton
    fun provideGroupDao(database: SyncroDB): GroupDao =
        database.groupDao()

    @Provides
    @Singleton
    fun provideReminderDao(database: SyncroDB): ReminderDao =
        database.reminderDao()

    @Provides
    @Singleton
    fun provideSolutionDao(database: SyncroDB): SolutionDao =
        database.solutionDao()

    @Provides
    @Singleton
    fun provideSourceFileDao(database: SyncroDB): SourceFileDao =
        database.sourceFileDao()

    @Provides
    @Singleton
    fun provideTaskDao(database: SyncroDB): TaskDao =
        database.taskDao()

    @Provides
    @Singleton
    fun provideUserDao(database: SyncroDB): UserDao =
        database.userDao()

}