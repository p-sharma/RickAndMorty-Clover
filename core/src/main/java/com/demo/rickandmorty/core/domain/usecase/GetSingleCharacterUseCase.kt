package com.demo.rickandmorty.core.domain.usecase

import com.demo.rickandmorty.core.domain.Results
import com.demo.rickandmorty.core.model.CharacterItem
import kotlinx.coroutines.flow.Flow

interface GetSingleCharacterUseCase {
    fun execute(charId: Int): Flow<Results<CharacterItem>>
}