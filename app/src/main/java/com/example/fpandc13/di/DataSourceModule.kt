package com.example.fpandc13.di

import com.example.fpandc13.data.local.datasource.UserLocalDataSource
import com.example.fpandc13.data.local.datasource.UserLocalDataSourceImpl
import com.example.fpandc13.data.network.datasource.AuthRemoteDataSource
import com.example.fpandc13.data.network.datasource.AuthRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun provideAuthRemoteDataSource(authRemoteDataSourceImpl: AuthRemoteDataSourceImpl): AuthRemoteDataSource

    @Binds
    abstract fun provideUserLocalDataSource(userLocalDataSourceImpl: UserLocalDataSourceImpl): UserLocalDataSource
}