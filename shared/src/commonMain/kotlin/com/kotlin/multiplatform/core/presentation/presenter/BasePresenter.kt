package com.kotlin.multiplatform.core.presentation.presenter

import com.kotlin.multiplatform.core.presentation.BaseAction
import com.kotlin.multiplatform.core.presentation.BaseEffect
import com.kotlin.multiplatform.core.presentation.BaseViewModel
import com.kotlin.multiplatform.core.presentation.BaseViewState
import com.kotlin.multiplatform.core.presentation.EffectDispatcher
import com.kotlin.multiplatform.core.presentation.viewcontract.BaseViewContract
import com.kotlin.multiplatform.core.presentation.viewstore.BaseViewStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter : CoroutineScope {

    private var job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Default + job

    protected lateinit var actions: Flow<BaseAction>
    protected lateinit var effectDispatcher: EffectDispatcher

    fun bind(
        actions: Flow<BaseAction>,
        effectDispatcher: EffectDispatcher,
    ) {
        this.actions = actions
        this.effectDispatcher = effectDispatcher
        launch { onBind() }
    }

    fun unbind() {
        job.cancel()
    }

    protected abstract suspend fun onBind()
    protected suspend fun onUnbind() {}

    protected suspend inline fun <reified T : BaseAction> collectActions(crossinline block: (T) -> Unit) {
        actions.filterIsInstance<T>().collect {
            block(it)
        }
    }

    protected suspend inline fun <reified T : BaseEffect> collectEffects(crossinline block: (T) -> Unit) {
        effectDispatcher.observe().filterIsInstance<T>().collect {
            block(it)
        }
    }
}