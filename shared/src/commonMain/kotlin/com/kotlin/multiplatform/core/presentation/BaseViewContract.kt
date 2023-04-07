package com.kotlin.multiplatform.core.presentation

interface BaseViewContract<ViewModel : BaseViewModel> {
    fun render(viewModel: ViewModel) {}
}
