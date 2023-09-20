package com.demo.rickandmorty.domain.usecase

import com.demo.rickandmorty.core.domain.Results
import com.demo.rickandmorty.core.domain.usecase.GetSingleCharacterUseCase
import com.demo.rickandmorty.core.model.CharacterItem
import com.demo.rickandmorty.core.remote.ApiResponse
import com.demo.rickandmorty.core.remote.CharacterDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class GetSingleCharacterUseCaseImpl @Inject constructor(
    private val characterDataSource: CharacterDataSource
) : GetSingleCharacterUseCase {

    override fun execute(charId: Int): Flow<Results<CharacterItem>> = flow {
        emit(Results.Loading)
        try {
            characterDataSource.getSingleCharacter(charId).collect { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        emit(Results.Success(response.data))
                    }

                    is ApiResponse.Empty -> {
                        emit(Results.Success(null))
                    }
                }
            }
        } catch (e: Exception) {
            emit(Results.Error(errorMessage = e.localizedMessage, errorCode = null))
        }
    }.flowOn(Dispatchers.Default)
}