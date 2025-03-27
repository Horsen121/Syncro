package com.example.syncro.application

import android.app.Application
import android.content.Context
import androidx.annotation.StringRes
import androidx.room.Room
import com.example.syncro.data.datasourse.local.SyncroDB
import com.example.syncro.data.repository.GroupRepositoryImpl
import com.example.syncro.data.repository.ReminderRepositoryImpl
import com.example.syncro.data.repository.SolutionRepositoryImpl
import com.example.syncro.data.repository.TaskRepositoryImpl
import com.example.syncro.data.repository.UserRepositoryImpl
import com.example.syncro.domain.repository.GroupRepository
import com.example.syncro.domain.repository.ReminderRepository
import com.example.syncro.domain.repository.SolutionRepository
import com.example.syncro.domain.repository.TaskRepository
import com.example.syncro.domain.repository.UserRepository
import com.example.syncro.domain.usecases.GroupUseCases
import com.example.syncro.domain.usecases.ReminderUseCases
import com.example.syncro.domain.usecases.SolutionUseCases
import com.example.syncro.domain.usecases.TaskUseCases
import com.example.syncro.domain.usecases.UserUseCases
import com.example.syncro.domain.usecases.group.AddGroup
import com.example.syncro.domain.usecases.group.DeleteGroup
import com.example.syncro.domain.usecases.group.GetGroup
import com.example.syncro.domain.usecases.group.GetGroups
import com.example.syncro.domain.usecases.reminder.AddReminder
import com.example.syncro.domain.usecases.reminder.DeleteReminder
import com.example.syncro.domain.usecases.reminder.GetReminder
import com.example.syncro.domain.usecases.reminder.GetReminders
import com.example.syncro.domain.usecases.solution.AddSolution
import com.example.syncro.domain.usecases.solution.DeleteSolution
import com.example.syncro.domain.usecases.solution.GetSolution
import com.example.syncro.domain.usecases.solution.GetSolutions
import com.example.syncro.domain.usecases.task.AddTask
import com.example.syncro.domain.usecases.task.DeleteTask
import com.example.syncro.domain.usecases.task.GetAllTasks
import com.example.syncro.domain.usecases.task.GetTask
import com.example.syncro.domain.usecases.task.GetTasks
import com.example.syncro.domain.usecases.user.AddUser
import com.example.syncro.domain.usecases.user.DeleteUser
import com.example.syncro.domain.usecases.user.GetUser
import com.example.syncro.domain.usecases.user.GetUsers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSyncroDB(app: Application): SyncroDB {
        return Room.databaseBuilder(
            app,
            SyncroDB::class.java,
            SyncroDB.DATABASE_NAME
        ).build()
    }

    @Singleton
    class StringResourceProvider @Inject constructor(
        @ApplicationContext private val context: Context
    ) {
        fun getString(@StringRes resId: Int): String {
            return context.getString(resId)
        }

        fun getString(@StringRes resId: Int, vararg formatArgs: Any): String {
            return context.getString(resId, *formatArgs)
        }
    }

    @Provides
    @Singleton
    fun provideTaskRepository(db: SyncroDB): TaskRepository {
        return TaskRepositoryImpl(db.taskDao())
    }
    @Provides
    @Singleton
    fun provideTaskUseCases(repository: TaskRepository): TaskUseCases {
        return TaskUseCases(
            getAllTasks = GetAllTasks(repository),
            getTasks = GetTasks(repository),
            addTask = AddTask(repository),
            getTask= GetTask(repository),
            deleteTask = DeleteTask(repository)
        )
    }

    @Provides
    @Singleton
    fun provideGroupRepository(db: SyncroDB): GroupRepository {
        return GroupRepositoryImpl(db.groupDao())
    }
    @Provides
    @Singleton
    fun provideGroupUseCases(repository: GroupRepository): GroupUseCases {
        return GroupUseCases(
            getGroups = GetGroups(repository),
            addGroup = AddGroup(repository),
            getGroup= GetGroup(repository),
            deleteGroup = DeleteGroup(repository)
        )
    }

    @Provides
    @Singleton
    fun provideSolutionRepository(db: SyncroDB): SolutionRepository {
        return SolutionRepositoryImpl(db.solutionDao())
    }
    @Provides
    @Singleton
    fun provideSolutionUseCases(repository: SolutionRepository): SolutionUseCases {
        return SolutionUseCases(
            getSolutions = GetSolutions(repository),
            addSolution = AddSolution(repository),
            getSolution= GetSolution(repository),
            deleteSolution = DeleteSolution(repository)
        )
    }

    @Provides
    @Singleton
    fun provideReminderRepository(db: SyncroDB): ReminderRepository {
        return ReminderRepositoryImpl(db.reminderDao())
    }
    @Provides
    @Singleton
    fun provideReminderUseCases(repository: ReminderRepository): ReminderUseCases {
        return ReminderUseCases(
            getReminders = GetReminders(repository),
            addReminder = AddReminder(repository),
            getReminder= GetReminder(repository),
            deleteReminder = DeleteReminder(repository)
        )
    }

    @Provides
    @Singleton
    fun provideUserRepository(db: SyncroDB): UserRepository {
        return UserRepositoryImpl(db.userDao())
    }
    @Provides
    @Singleton
    fun provideUserUseCases(repository: UserRepository): UserUseCases {
        return UserUseCases(
            getUsers = GetUsers(repository),
            addUser = AddUser(repository),
            getUser = GetUser(repository),
            deleteUser = DeleteUser(repository)
        )
    }
}