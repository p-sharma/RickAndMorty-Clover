package com.demo.rickandmorty.core.remote

import com.demo.rickandmorty.core.model.CharacterItem
import com.demo.rickandmorty.core.model.CharacterListResponse
import com.demo.rickandmorty.core.model.LocationItem
import kotlinx.coroutines.flow.Flow

interface CharacterDataSource {
    fun getCharacterList(): Flow<ApiResponse<CharacterListResponse>>
    fun getSingleCharacter(charId: Int): Flow<ApiResponse<CharacterItem>>
    fun getCharacterLocation(locationId: Int): Flow<ApiResponse<LocationItem>>
}