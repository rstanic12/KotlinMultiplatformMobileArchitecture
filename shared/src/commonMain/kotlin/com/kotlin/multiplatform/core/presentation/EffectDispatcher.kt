package com.kotlin.multiplatform.core.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class EffectDispatcher : CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    private val effects = MutableSharedFlow<BaseEffect>()

    fun observe(): Flow<BaseEffect> = effects.asSharedFlow()

    fun emit(effect: BaseEffect) = launch {
        println("Emit Effect - $effect")
        effects.emit(effect)
    }
}
