package com.demo.rickandmorty.character.detail

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.demo.rickandmorty.core.CHAR_DETAIL_ARG
import com.demo.rickandmorty.core.ui.Destinations

fun NavGraphBuilder.characterDetailScreen(
    navigateBack: () -> Unit,
) {
    composable(
        route = Destinations.CharacterDetails.route,
        arguments = listOf(navArgument(name = CHAR_DETAIL_ARG) { type = NavType.IntType })
    ) {
        val viewModel: CharacterDetailViewModel = hiltViewModel()

        val characterUiState by viewModel.detailState.collectAsStateWithLifecycle()

        CharacterDetailScreen(
            characterDetailUiState = characterUiState,
            onIconBackPressed = { navigateBack() }
        )
    }
}