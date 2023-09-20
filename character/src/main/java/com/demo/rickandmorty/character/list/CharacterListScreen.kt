package com.demo.rickandmorty.character.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.demo.rickandmorty.character.ErrorContent
import com.demo.rickandmorty.character.R

@ExperimentalMaterial3Api
@Composable
internal fun CharacterListScreen(
    characterListUiState: CharacterListUiState,
    onCardClicked: (Int) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier,
                title = { Text(text = stringResource(id = R.string.screen_characters)) },
            )
        },
        content = { paddingValues ->
            CharacterListContent(
                modifier = Modifier
                    .padding(paddingValues = paddingValues)
                    .padding(8.dp),
                characterListUiState = characterListUiState,
                onCardClicked = { onCardClicked(it) }
            )
        },
    )
}


@ExperimentalMaterial3Api
@Composable
internal fun CharacterListContent(
    modifier: Modifier = Modifier,
    characterListUiState: CharacterListUiState,
    onCardClicked: (Int) -> Unit,
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(8.dp))
        when (characterListUiState) {
            is CharacterListUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
            is CharacterListUiState.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(count = characterListUiState.data?.size ?: 0) { index ->
                        val data = characterListUiState.data?.get(index)
                        CharacterCard(
                            modifier = Modifier.fillMaxWidth(),
                            id = data?.id ?: 0,
                            image = data?.image.orEmpty(),
                            name = data?.name.orEmpty(),
                            status = data?.status.orEmpty(),
                            species = data?.species.orEmpty(),
                            onCardClicked = { onCardClicked(it) },
                        )
                    }
                }
            }

            is CharacterListUiState.Error -> {
                ErrorContent(
                    modifier = Modifier.fillMaxSize(),
                    errorMessage = characterListUiState.errorMessage.orEmpty(),
                    errorCode = characterListUiState.errorCode ?: 0,
                )
            }
        }
    }
}