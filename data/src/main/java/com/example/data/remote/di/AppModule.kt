package com.example.data.remote.di

import com.example.data.remote.NetworkApi
import com.example.data.remote.NetworkApi.Companion.BASE_URL
import com.example.data.repository.MarvelRepoImpl
import com.example.domain.repository.MarvelRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesNetworkApi(): NetworkApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkApi::class.java)
    }

    @Provides
    @Singleton
    fun providesMarvelRepo(api: NetworkApi): MarvelRepo {
        return MarvelRepoImpl(api)
    }
}


