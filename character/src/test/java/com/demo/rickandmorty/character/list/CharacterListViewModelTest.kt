package com.demo.rickandmorty.character.list

import app.cash.turbine.test
import com.demo.rickandmorty.character.DefaultSharingStrategyProvider
import com.demo.rickandmorty.character.TestDispatcherProvider
import com.demo.rickandmorty.core.DispatcherProvider
import com.demo.rickandmorty.core.SharingStrategyProvider
import com.demo.rickandmorty.core.domain.Results
import com.demo.rickandmorty.core.domain.usecase.GetCharacterListUseCase
import com.demo.rickandmorty.core.model.CharacterItem
import com.demo.rickandmorty.core.model.CharacterListResponse
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
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
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        runTest {
            doReturn(flowOf(Results.Success<CharacterListResponse>())).`when`(characterListUseCase).execute()

            val viewModel = CharacterListViewModel(characterListUseCase, dispatcherProvider)

//            backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
//                characterListUseCase.execute().collect(flow<Results<CharacterListResponse>>)
//            }

            viewModel.characterListUiState.test {
                assertEquals(CharacterListUiState.Loading, awaitItem())
                assertEquals(CharacterListUiState.Success(emptyList<CharacterItem>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(characterListUseCase).execute()
        }
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        runTest {
            val errorMessage = "Error occured..."
            doReturn(flow<List<CharacterListResponse>> {
                throw IllegalStateException(errorMessage)
            }).`when`(characterListUseCase).execute()

            val viewModel = CharacterListViewModel(characterListUseCase, dispatcherProvider)
            viewModel.characterListUiState.test {
                assertEquals(CharacterListUiState.Loading, awaitItem())
                assertEquals(CharacterListUiState.Error(IllegalStateException(errorMessage).toString()), awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
            verify(characterListUseCase).execute()
        }
    }

}