package com.example.domain.di

import com.example.domain.repository.MarvelRepo
import com.example.domain.useCases.GetCharacterDetailsUseCase
import com.example.domain.useCases.GetCharacterListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideGetCharacterListUseCase(marvelRepo: MarvelRepo): GetCharacterListUseCase {
        return GetCharacterListUseCase(marvelRepo)
    }

    @Provides
    fun provideGetCharacterDetailsUseCase(marvelRepo: MarvelRepo): GetCharacterDetailsUseCase {
        return GetCharacterDetailsUseCase(marvelRepo)
    }

}