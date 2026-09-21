package com.example.login

import com.example.login.data.LoginRepositoryImpl
import com.example.login.domain.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class LoginModule {
    @Binds
    abstract fun bindAuthRepository(impl: LoginRepositoryImpl): LoginRepository
}