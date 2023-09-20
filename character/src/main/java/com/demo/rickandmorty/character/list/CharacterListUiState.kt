package com.demo.rickandmorty.character.list

import com.demo.rickandmorty.core.model.CharacterItem

sealed interface CharacterListUiState {
    object Loading : CharacterListUiState
    data class Success(val data: List<CharacterItem>? = emptyList()) : CharacterListUiState
    data class Error(
        val errorMessage: String? = null,
        val errorCode: Int? = null,
    ) : CharacterListUiState
}