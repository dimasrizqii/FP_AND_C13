package com.example.fpandc13.di

import com.example.fpandc13.data.network.service.auth.AeroplaneAuthApiInterface
import com.example.fpandc13.data.network.service.booking.AeroplaneBookingApiInterface
import com.example.fpandc13.data.network.service.passenger.AeroplanePassengerApiInterface
import com.example.fpandc13.data.network.service.ticket.AeroplaneTicketApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    const val BASE_URL = "https://final-be-project-aeroplane-production.up.railway.app/"


    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideOkHttp(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()}


        private  val logging : HttpLoggingInterceptor
        get(){
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }




    private val client = OkHttpClient.Builder().addInterceptor(logging).build()

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Singleton
    @Provides
    fun provideAuthApi(retrofit: Retrofit): AeroplaneAuthApiInterface =
        retrofit.create(AeroplaneAuthApiInterface::class.java)

    @Singleton
    @Provides
    fun provideTicketApi(retrofit: Retrofit): AeroplaneTicketApiInterface =
        retrofit.create(AeroplaneTicketApiInterface::class.java)

    @Singleton
    @Provides
    fun provideBookingApi(retrofit: Retrofit): AeroplaneBookingApiInterface =
        retrofit.create(AeroplaneBookingApiInterface::class.java)

    @Singleton
    @Provides
    fun providePassengerApi(retrofit: Retrofit): AeroplanePassengerApiInterface =
        retrofit.create(AeroplanePassengerApiInterface::class.java)
}