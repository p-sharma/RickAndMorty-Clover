package com.demo.rickandmorty.core.model

data class PageInfo(
    val next: String? = null,
    val pages: Int? = null,
    val prev: Any? = null,
    val count: Int? = null,
)