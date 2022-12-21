package com.example.fpandc13.di

import com.example.fpandc13.data.repository.AuthRepository
import com.example.fpandc13.data.repository.AuthRepositoryImpl
import com.example.fpandc13.data.repository.UserRepository
import com.example.fpandc13.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}