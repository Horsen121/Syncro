package com.example.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CurrentUserDataModule {
    @Binds
    @Singleton
    abstract fun bindCurrentUserRepository(impl: CurrentUserRepositoryImpl): CurrentUserRepository
}