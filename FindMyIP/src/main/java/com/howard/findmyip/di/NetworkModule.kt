package com.howard.findmyip.di

import com.google.gson.Gson
import com.howard.findmyip.model.Constants.BASE_URL
import com.howard.findmyip.model.FindMyIpApi
import com.howard.findmyip.repository.FindMyIpRepo
import com.howard.findmyip.repository.FindMyIpRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideGson(): Gson = Gson()

    @Provides
    fun provideHttpInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    @Provides
    fun provideOKHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

    @Provides
    fun provideRetrofit(
        gson: Gson, okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson)).client(okHttpClient).build()

    @Provides
    fun provideRepository(
        apiDetails: FindMyIpApi
    ) :FindMyIpRepo {
        return FindMyIpRepositoryImpl(apiDetails)
    }

    @Provides
    fun provideApiDetails(
        retrofit: Retrofit
    ): FindMyIpApi = retrofit.create(FindMyIpApi::class.java)


}
