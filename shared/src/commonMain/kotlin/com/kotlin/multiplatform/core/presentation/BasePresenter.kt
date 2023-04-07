package com.kotlin.multiplatform.core.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<
    ViewState : BaseViewState,
    ViewEvent : BaseViewEvent,
    ViewModel : BaseViewModel,
    ViewContract : BaseViewContract<ViewModel>,
    ViewStore : BaseViewStore<ViewState, ViewEvent, ViewModel, ViewContract>>(
    private val viewStore: ViewStore
) : CoroutineScope {

    private var job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Default + job

    protected lateinit var actionDispatcher: ActionDispatcher

    fun bind(actionDispatcher: ActionDispatcher, viewContract: ViewContract) {
        this.actionDispatcher = actionDispatcher
        viewStore.bind(viewContract)
        launch { onBind() }
    }

    fun unbind() {
        job.cancel()
    }

    protected abstract suspend fun onBind()
    protected suspend fun onUnbind() {}

    protected fun render(viewState: ViewState) {
        viewStore.reduce(viewState)
    }

    protected fun trigger(viewEvent: ViewEvent) {
        viewStore.event(viewEvent)
    }

    protected suspend inline fun <reified T : BaseAction> collect(crossinline block: (T) -> Unit) {
        actionDispatcher.observe().filterIsInstance<T>().collect {
            block(it)
        }
    }
}