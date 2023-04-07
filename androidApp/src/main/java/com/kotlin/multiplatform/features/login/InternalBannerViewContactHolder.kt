package com.kotlin.multiplatform.features.login

import androidx.compose.runtime.mutableStateOf
import com.kotlin.multiplatform.features.login.internet.InternetBannerViewContract
import com.kotlin.multiplatform.features.login.internet.InternetBannerViewModel

class InternalBannerViewContactHolder : InternetBannerViewContract {
    val viewModel = mutableStateOf(InternetBannerViewModel())

    override fun render(viewModel: InternetBannerViewModel) {
        this.viewModel.value = viewModel
    }
}