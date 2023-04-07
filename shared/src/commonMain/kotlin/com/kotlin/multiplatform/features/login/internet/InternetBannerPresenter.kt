package com.kotlin.multiplatform.features.login.internet

import com.kotlin.multiplatform.core.presentation.presenter.RenderablePresenter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class InternetBannerPresenter : RenderablePresenter<
    InternetBannerViewState,
    InternetBannerViewModel,
    InternetBannerViewStore>(
    InternetBannerViewStore()
) {
    override suspend fun onBind() {
        launch {
            var internetAvailable = false
            while (true) {
                internetAvailable = !internetAvailable
                onChange(internetAvailable)
                delay(1000)
            }
        }
    }

    private fun onChange(isInternetAvailable: Boolean) = when (isInternetAvailable) {
        false -> render(InternetBannerViewState.Show)
        true -> render(InternetBannerViewState.Hide)
    }
}