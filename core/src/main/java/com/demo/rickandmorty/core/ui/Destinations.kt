package com.demo.rickandmorty.core.ui

import com.demo.rickandmorty.core.CHAR_DETAIL_ARG

sealed class Destinations(val route: String) {

    object CharacterList: Destinations(route = "character_list")
    object CharacterDetails: Destinations(route = "character_detail_route?$CHAR_DETAIL_ARG={$CHAR_DETAIL_ARG}") {
        fun passCharId(charId: Int) = "character_detail_route?$CHAR_DETAIL_ARG=$charId"
    }
}