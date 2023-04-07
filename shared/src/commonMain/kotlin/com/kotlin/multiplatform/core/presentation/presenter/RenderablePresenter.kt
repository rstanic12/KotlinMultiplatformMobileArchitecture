package com.kotlin.multiplatform.core.presentation.presenter

import com.kotlin.multiplatform.core.presentation.BaseAction
import com.kotlin.multiplatform.core.presentation.BaseViewModel
import com.kotlin.multiplatform.core.presentation.BaseViewState
import com.kotlin.multiplatform.core.presentation.EffectDispatcher
import com.kotlin.multiplatform.core.presentation.viewcontract.BaseViewContract
import com.kotlin.multiplatform.core.presentation.viewstore.RenderableViewStore
import kotlinx.coroutines.flow.Flow

abstract class RenderablePresenter<
    ViewState : BaseViewState,
    ViewModel : BaseViewModel,
    ViewStore : RenderableViewStore<ViewState, ViewModel>>(
    private val viewStore: ViewStore
) : BasePresenter() {

    fun bind(
        actions: Flow<BaseAction>,
        effectDispatcher: EffectDispatcher,
        viewContract: BaseViewContract<ViewModel>
    ) {
        this.bind(actions, effectDispatcher)
        viewStore.bind(viewContract)
    }

    protected fun render(viewState: ViewState) {
        viewStore.reduce(viewState)
    }
}