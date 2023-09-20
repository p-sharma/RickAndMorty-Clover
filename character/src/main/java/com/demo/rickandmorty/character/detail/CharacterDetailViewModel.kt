package com.demo.rickandmorty.character.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.rickandmorty.core.CHAR_DETAIL_ARG
import com.demo.rickandmorty.core.domain.Results
import com.demo.rickandmorty.core.domain.usecase.GetSingleCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    singleCharacterUseCase: GetSingleCharacterUseCase,
) : ViewModel() {
    private val charId = savedStateHandle.getStateFlow(CHAR_DETAIL_ARG, 0)
    val detailState: StateFlow<CharacterDetailUiState> =
        singleCharacterUseCase.execute(charId.value).map { response ->
            when (response) {
                is Results.Loading -> CharacterDetailUiState.Loading
                is Results.Success -> CharacterDetailUiState.Success(response.data)
                is Results.Error -> CharacterDetailUiState.Error(
                    errorMessage = response.errorMessage,
                    errorCode = response.errorCode
                )
            }
        }.stateIn(
            scope = viewModelScope,
            initialValue = CharacterDetailUiState.Loading,
            started = SharingStarted.WhileSubscribed(5000L)
        )
}