package com.kotlin.multiplatform.features.login.internet

import com.kotlin.multiplatform.core.presentation.viewstore.RenderableViewStore

class InternetBannerViewStore : RenderableViewStore<InternetBannerViewState, InternetBannerViewModel>(InternetBannerViewModel()) {
    override fun reduce(viewState: InternetBannerViewState) = InternetBannerViewModel()
        .apply { render(this) }
}