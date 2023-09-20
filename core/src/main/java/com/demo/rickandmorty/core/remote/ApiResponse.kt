package com.demo.rickandmorty.core.remote

sealed interface ApiResponse<out T> {
    object Empty : ApiResponse<Nothing>
    data class Success<T>(val data: T? = null) : ApiResponse<T>
}