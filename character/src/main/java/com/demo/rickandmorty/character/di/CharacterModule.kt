package com.demo.rickandmorty.character.di

import com.demo.rickandmorty.character.DefaultDispatcherProvider
import com.demo.rickandmorty.character.DefaultSharingStrategyProvider
import com.demo.rickandmorty.core.DispatcherProvider
import com.demo.rickandmorty.core.SharingStrategyProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class CharacterModule {
    @Binds
    abstract fun bindDispatcherProvider(dispatcherProvider: DefaultDispatcherProvider): DispatcherProvider

    @Binds
    abstract fun bindSharingStrategyProvider(sharingStrategyProvider: DefaultSharingStrategyProvider): SharingStrategyProvider

}