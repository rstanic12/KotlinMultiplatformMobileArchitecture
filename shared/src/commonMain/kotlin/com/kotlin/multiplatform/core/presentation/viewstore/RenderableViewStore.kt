package com.kotlin.multiplatform.core.presentation.viewstore

import com.kotlin.multiplatform.core.presentation.BaseViewModel
import com.kotlin.multiplatform.core.presentation.BaseViewState
import com.kotlin.multiplatform.core.presentation.viewcontract.BaseViewContract
import com.kotlin.multiplatform.features.login.LoginViewModel

abstract class RenderableViewStore<
    ViewState : BaseViewState,
    ViewModel : BaseViewModel>(
    initialViewModel: ViewModel
) : BaseViewStore<ViewState, ViewModel, BaseViewContract<ViewModel>>(
    initialViewModel
) {
    abstract fun reduce(viewState: ViewState): ViewModel
}