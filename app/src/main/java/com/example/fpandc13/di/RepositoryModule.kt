package com.example.fpandc13.di

import com.example.fpandc13.data.repository.AeroplaneAuthRepository
import com.example.fpandc13.data.repository.AeroplaneAuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideAeroplaneAuthRepository(authRepositoryImpl: AeroplaneAuthRepositoryImpl) : AeroplaneAuthRepository
}