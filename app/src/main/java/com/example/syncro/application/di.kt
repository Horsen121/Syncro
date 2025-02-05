package com.example.syncro.application

import android.app.Application
import android.content.Context
import androidx.annotation.StringRes
import androidx.room.Room
import com.example.syncro.data.datasourse.local.SyncroDB
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
        )
            .createFromAsset("startSyncroDB.db")
            .build()
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

//    @Provides
//    @Singleton
//    fun provideMyRepository(db: SyncroDB): MyRepository {
//        return MyRepositoryImpl()
//    }
//    @Provides
//    @Singleton
//    fun provideSettingsUseCases(repository: SettingsRepository): SettingsUseCases {
//        return SettingsUseCases(
//            addSettings = AddSettings(repository),
//            getSettings = GetSettings(repository),
//            deleteSettings = DeleteSettings(repository)
//        )
//    }
}