package com.kotlin.multiplatform.core.presentation.viewcontract

import com.kotlin.multiplatform.core.presentation.BaseViewModel

interface ViewContract<ViewModel : BaseViewModel> {
    fun render(viewModel: ViewModel) {}
}
