package com.demo.rickandmorty.core.domain.usecase

import com.demo.rickandmorty.core.domain.Results
import com.demo.rickandmorty.core.model.CharacterListResponse
import kotlinx.coroutines.flow.Flow

interface GetCharacterListUseCase {
    fun execute(): Flow<Results<CharacterListResponse>>
}