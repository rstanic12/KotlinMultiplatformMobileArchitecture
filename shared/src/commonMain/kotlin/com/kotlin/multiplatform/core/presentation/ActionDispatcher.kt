package com.kotlin.multiplatform.core.presentation

import com.kotlin.multiplatform.core.presentation.presenter.BasePresenter
import com.kotlin.multiplatform.core.presentation.presenter.RenderablePresenter
import com.kotlin.multiplatform.core.presentation.viewcontract.ViewContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ActionDispatcher : CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    private val effectDispatcher = EffectDispatcher()
    private val actions = MutableSharedFlow<BaseAction>()

    fun bind(presenter: BasePresenter) {
        presenter.bind(
            actions.asSharedFlow(),
            effectDispatcher,
        )
    }

    fun <ViewModel : BaseViewModel> bind(
        presenter: RenderablePresenter<*, ViewModel, *>,
        viewContract: ViewContract<ViewModel>
    ) {
        presenter.bind(
            actions.asSharedFlow(),
            effectDispatcher,
            viewContract
        )
    }

    fun emit(action: BaseAction) = launch {
        println("Emit Action - $action")
        actions.emit(action)
    }
}