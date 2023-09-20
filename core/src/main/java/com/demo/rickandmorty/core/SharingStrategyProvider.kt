package com.demo.rickandmorty.core

import kotlinx.coroutines.flow.SharingStarted

interface SharingStrategyProvider {
    val lazily: SharingStarted
    val eagerly: SharingStarted
    val whileSubscribed: SharingStarted
}