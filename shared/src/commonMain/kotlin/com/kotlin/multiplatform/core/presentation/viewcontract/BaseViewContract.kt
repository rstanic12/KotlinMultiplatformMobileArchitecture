package com.kotlin.multiplatform.core.presentation.viewcontract

import com.kotlin.multiplatform.core.presentation.BaseViewModel

interface BaseViewContract<ViewModel : BaseViewModel> {
    fun render(viewModel: ViewModel)
}
