package com.demo.rickandmorty.character

import com.demo.rickandmorty.core.SharingStrategyProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted

@ExperimentalCoroutinesApi
class TestSharingStrategyProvider : SharingStrategyProvider {
    override val lazily: SharingStarted
        get() = SharingStarted.Lazily
    override val eagerly: SharingStarted
        get() = SharingStarted.Lazily
    override val whileSubscribed: SharingStarted
        get() = SharingStarted.Lazily

}