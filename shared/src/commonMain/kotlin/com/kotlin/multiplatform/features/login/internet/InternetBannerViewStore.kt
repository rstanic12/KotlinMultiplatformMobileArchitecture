package com.kotlin.multiplatform.features.login.internet

import com.kotlin.multiplatform.core.presentation.viewstore.RenderableViewStore

class InternetBannerViewStore : RenderableViewStore<InternetBannerViewState, InternetBannerViewModel>(InternetBannerViewModel()) {
    override fun reduce(viewState: InternetBannerViewState) = when (viewState) {
        is InternetBannerViewState.Show -> viewModel.copy(isVisible = true)
        is InternetBannerViewState.Hide -> viewModel.copy(isVisible = false)
    }.apply { render(this) }
}