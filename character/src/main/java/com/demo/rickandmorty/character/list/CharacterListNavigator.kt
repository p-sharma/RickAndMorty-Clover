package com.demo.rickandmorty.character.list

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.demo.rickandmorty.core.ui.Destinations

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.characterListScreen(
    navigateToDetail: (Int) -> Unit,
) {
    composable(route = Destinations.CharacterList.route) {
        val viewModel: CharacterListViewModel = hiltViewModel()

        val characterUiState by viewModel.characterListUiState.collectAsStateWithLifecycle()

        CharacterListScreen(
            characterListUiState = characterUiState,
            onCardClicked = { navigateToDetail(it) }
        )
    }
}