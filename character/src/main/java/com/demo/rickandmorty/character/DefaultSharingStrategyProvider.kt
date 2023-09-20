package com.demo.rickandmorty.character

import com.demo.rickandmorty.core.DispatcherProvider
import com.demo.rickandmorty.core.SharingStrategyProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import javax.inject.Inject

class DefaultSharingStrategyProvider @Inject constructor() : SharingStrategyProvider {
    override val lazily: SharingStarted
        get() = SharingStarted.Lazily

    override val eagerly: SharingStarted
        get() = SharingStarted.Eagerly

    override val whileSubscribed: SharingStarted
        get() = SharingStarted.WhileSubscribed()


}