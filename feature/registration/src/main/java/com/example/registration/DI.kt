package com.example.registration

import com.example.registration.data.RegistrationRepositoryImpl
import com.example.registration.domain.RegistrationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class RegistrationModule {
    @Binds
    abstract fun bindRegistrationRepository(impl: RegistrationRepositoryImpl): RegistrationRepository
}