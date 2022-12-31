package com.example.fpandc13.di

import com.example.fpandc13.data.local.datasource.UserLocalDataSource
import com.example.fpandc13.data.local.datasource.UserLocalDataSourceImpl
import com.example.fpandc13.data.network.datasource.*
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

    @Binds
    abstract fun provideTicketRemoteDataSource(authRemoteDataSourceImpl: TicketRemoteDataSourceImpl): TicketRemoteDataSource

    @Binds
    abstract fun provideBookingDataSource(userLocalDataSourceImpl: BookingRemoteDataSourceImpl): BookingRemoteDataSource


    @Binds
    abstract fun providePassengerDataSource(userLocalDataSourceImpl: PassengerRemoteDataSourceImpl): PassengerRemoteDataSource
}