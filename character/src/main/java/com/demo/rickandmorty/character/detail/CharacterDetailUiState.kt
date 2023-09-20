package com.demo.rickandmorty.character.detail

import com.demo.rickandmorty.core.model.CharacterItem

sealed interface CharacterDetailUiState {
    object Loading : CharacterDetailUiState
    data class Success(val data: CharacterItem? = null) : CharacterDetailUiState
    data class Error(
        val errorMessage: String? = null,
        val errorCode: Int? = null,
    ) : CharacterDetailUiState
}