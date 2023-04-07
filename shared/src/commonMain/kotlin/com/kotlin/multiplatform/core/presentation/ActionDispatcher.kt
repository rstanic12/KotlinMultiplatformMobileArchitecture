package com.kotlin.multiplatform.core.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ActionDispatcher : CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    private val actions = MutableSharedFlow<BaseAction>()

    fun <ViewContract : BaseViewContract<*>> bind(presenter: BasePresenter<*, *, *, ViewContract, *>, viewContract: ViewContract) {
        presenter.bind(this, viewContract)
    }

    fun unbind(presenter: BasePresenter<*, *, *, *, *>) {
        presenter.unbind()
    }

    fun observe(): Flow<BaseAction> = actions.asSharedFlow()

    fun emit(action: BaseAction) = launch {
        println("Emit Action - $action")
        actions.emit(action)
    }
}