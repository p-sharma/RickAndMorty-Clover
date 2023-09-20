package com.demo.rickandmorty.domain.di

import com.demo.rickandmorty.core.domain.usecase.GetCharacterListUseCase
import com.demo.rickandmorty.core.domain.usecase.GetSingleCharacterUseCase
import com.demo.rickandmorty.domain.usecase.GetCharacterListUseCaseImpl
import com.demo.rickandmorty.domain.usecase.GetSingleCharacterUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DomainUseCaseModule {
    @Binds
    abstract fun bindCharacterListUseCase(characterListUseCase: GetCharacterListUseCaseImpl): GetCharacterListUseCase

    @Binds
    abstract fun bindSingleCharacterUseCase(singleCharacterUseCase: GetSingleCharacterUseCaseImpl): GetSingleCharacterUseCase
}