package com.demo.rickandmorty.network

import com.demo.rickandmorty.core.remote.ApiResponse
import com.demo.rickandmorty.core.model.CharacterItem
import com.demo.rickandmorty.core.model.CharacterListResponse
import com.demo.rickandmorty.core.model.LocationItem
import com.demo.rickandmorty.core.remote.CharacterDataSource
import com.demo.rickandmorty.network.api.RemoteApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CharacterDataSourceImpl @Inject constructor(
    private val api: RemoteApi
) : CharacterDataSource {

    override fun getCharacterList(): Flow<ApiResponse<CharacterListResponse>> = flow {
        val response = api.loadCharacters()
        if (response.isSuccessful && response.body() != null) {
            emit(ApiResponse.Success(response.body()))
        } else {
            emit(ApiResponse.Empty)
        }
    }.flowOn(Dispatchers.IO)

    override fun getSingleCharacter(charId: Int): Flow<ApiResponse<CharacterItem>> = flow {
        val response = api.getSingleCharacter(charId)
        if (response.isSuccessful && response.body() != null) {
            emit(ApiResponse.Success(response.body()))
        } else {
            emit(ApiResponse.Empty)
        }
    }.flowOn(Dispatchers.IO)

    override fun getCharacterLocation(locationId: String): Flow<ApiResponse<LocationItem>> = flow {
        val response = api.getLocationItem(locationId)
        if (response.isSuccessful && response.body() != null) {
            emit(ApiResponse.Success(response.body()))
        } else {
            emit(ApiResponse.Empty)
        }
    }.flowOn(Dispatchers.IO)
}