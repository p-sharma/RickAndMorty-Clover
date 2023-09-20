package com.demo.rickandmorty.character.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.rickandmorty.core.DispatcherProvider
import com.demo.rickandmorty.core.SharingStrategyProvider
import com.demo.rickandmorty.core.domain.Results
import com.demo.rickandmorty.core.domain.usecase.GetCharacterListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    characterListUseCase: GetCharacterListUseCase,
    dispatcherProvider: DispatcherProvider,
) : ViewModel() {
    val characterListUiState: StateFlow<CharacterListUiState> =
        characterListUseCase.execute()
            .flowOn(dispatcherProvider.io)
            .map { response ->
                when (response) {
                    is Results.Loading -> {
                        CharacterListUiState.Loading
                    }

                    is Results.Success -> {
                        CharacterListUiState.Success(data = response.data?.results)
                    }

                    is Results.Error -> {
                        CharacterListUiState.Error(
                            errorMessage = response.errorMessage,
                            errorCode = response.errorCode
                        )
                    }
                }
            }.stateIn(
                scope = viewModelScope,
                initialValue = CharacterListUiState.Loading,
                started = SharingStarted.WhileSubscribed(5000L)
            )
}