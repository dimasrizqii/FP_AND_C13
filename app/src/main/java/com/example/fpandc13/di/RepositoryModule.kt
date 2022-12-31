package com.example.fpandc13.di

import com.example.fpandc13.data.repository.*
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

    @Binds
    abstract fun provideTicketRepository(userRepositoryImpl: TicketRepositoryImpl): TicketRepository

    @Binds
    abstract fun provideBookingRepository(userRepositoryImpl: BookingRepositoryImpl): BookingRepository

}