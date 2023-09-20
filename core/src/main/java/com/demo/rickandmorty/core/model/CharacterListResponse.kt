package com.demo.rickandmorty.core.model

data class CharacterListResponse(
    val results: List<CharacterItem>? = null,
    val info: PageInfo? = null,
)
