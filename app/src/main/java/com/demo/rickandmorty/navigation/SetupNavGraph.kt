package com.demo.rickandmorty.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.demo.rickandmorty.character.detail.characterDetailScreen
import com.demo.rickandmorty.character.list.characterListScreen
import com.demo.rickandmorty.core.ui.Destinations

@Composable
fun SetupNavGraph(
    startDestination: String,
    navHostController: NavHostController,
) {
    NavHost(
        startDestination = startDestination,
        navController = navHostController,
    ) {
        characterListScreen(
            navigateToDetail = {
                navHostController.navigate(Destinations.CharacterDetails.passCharId(it)) {
                    launchSingleTop = true
                }
            }
        )
        characterDetailScreen(
            navigateBack = {
                navHostController.popBackStack()
            }
        )
    }
}