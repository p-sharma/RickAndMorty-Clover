package com.demo.rickandmorty.network.api

import com.demo.rickandmorty.core.model.CharacterItem
import com.demo.rickandmorty.core.model.CharacterListResponse
import com.demo.rickandmorty.core.model.LocationItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteApi {
    @GET("character")
    suspend fun loadCharacters(): Response<CharacterListResponse>

    @GET("character/{id}")
    suspend fun getSingleCharacter(@Path("id") charId: Int): Response<CharacterItem>

    @GET("location/{id}")
    suspend fun getLocationItem(@Path("id") locationId: Int): Response<LocationItem>
}