package com.kotlin.multiplatform.core.presentation

abstract class BaseViewStore<
    ViewState : BaseViewState,
    ViewEvent : BaseViewEvent,
    ViewModel : BaseViewModel,
    ViewContract : BaseViewContract<ViewModel>>(
    initialViewModel: ViewModel,
) {
    protected lateinit var viewContract: ViewContract
        private set

    protected var viewModel = initialViewModel
        private set

    fun bind(viewContract: ViewContract) {
        this.viewContract = viewContract
    }

    protected fun render(viewModel: ViewModel) {
        this.viewModel = viewModel
        viewContract.render(this.viewModel)
    }

    abstract fun reduce(viewState: ViewState)
    abstract fun event(viewEvent: ViewEvent)
}
