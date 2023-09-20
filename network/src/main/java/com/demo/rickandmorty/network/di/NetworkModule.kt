package com.demo.rickandmorty.network.di

import com.demo.rickandmorty.core.BASE_URL
import com.demo.rickandmorty.core.remote.CharacterDataSource
import com.demo.rickandmorty.network.CharacterDataSourceImpl
import com.demo.rickandmorty.network.api.RemoteApi
import com.demo.rickandmorty.network.api.RequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideHlHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS).addInterceptor(RequestInterceptor()).build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideNetworkService(retrofit: Retrofit): RemoteApi {
        return retrofit.create(RemoteApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: RemoteApi): CharacterDataSource {
        return CharacterDataSourceImpl(apiService)
    }
}