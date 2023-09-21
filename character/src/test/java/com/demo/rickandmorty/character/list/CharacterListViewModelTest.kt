package com.demo.rickandmorty.character.list

import app.cash.turbine.test

import com.demo.rickandmorty.character.TestDispatcherProvider
import com.demo.rickandmorty.core.DispatcherProvider
import com.demo.rickandmorty.core.domain.Results
import com.demo.rickandmorty.core.domain.usecase.GetCharacterListUseCase
import com.demo.rickandmorty.core.model.CharacterItem
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
@RunWith(MockitoJUnitRunner::class)
class CharacterListViewModelTest {
    @Mock
    private lateinit var characterListUseCase: GetCharacterListUseCase

    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `given server response is 200 when fetch data from remote datasource then viewmodel should return success`() {
        runTest {
            whenever(characterListUseCase.execute()) doReturn flowOf(Results.Success(listOf<CharacterItem>()))

            val viewModel = CharacterListViewModel(characterListUseCase, dispatcherProvider)

            viewModel.characterListUiState.test {
                assertEquals(CharacterListUiState.Success(emptyList<CharacterItem>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(characterListUseCase).execute()
        }
    }

    @Test
    fun `given server response is error when fetch data from remote datasource then viewmodel should return error`() {
        runTest {
            val errorMessage = "Error occured..."
            whenever(characterListUseCase.execute()) doReturn flowOf(Results.Error(errorMessage = IllegalStateException(errorMessage).toString()))

            val viewModel = CharacterListViewModel(characterListUseCase, dispatcherProvider)

            viewModel.characterListUiState.test {
                assertEquals(CharacterListUiState.Error(IllegalStateException(errorMessage).toString()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(characterListUseCase).execute()
        }
    }

}