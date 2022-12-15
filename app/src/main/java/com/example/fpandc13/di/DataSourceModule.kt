package com.example.fpandc13.di

import com.example.fpandc13.data.network.datasource.AeroplaneAuthDataSource
import com.example.fpandc13.data.network.datasource.AeroplaneAuthDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun provideAeroplaneAuthDataSource(authDataSourceImpl: AeroplaneAuthDataSourceImpl) : AeroplaneAuthDataSource
}