package com.kotlin.multiplatform.features.login.internet

import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.dismiss
import com.kotlin.multiplatform.core.presentation.navigation.DefaultRootComponent
import com.kotlin.multiplatform.core.presentation.navigation.SlotNavigation
import com.kotlin.multiplatform.core.presentation.presenter.RenderablePresenter
import com.kotlin.multiplatform.core.presentation.presenter.onMain
import kotlinx.coroutines.delay

class InternetBannerPresenter(
    private val navigation: SlotNavigation,
) : RenderablePresenter<
    InternetBannerViewState,
    InternetBannerViewModel,
    InternetBannerViewStore>(
    InternetBannerViewStore()
) {
    override suspend fun onBind() {
        var internetAvailable = false
        while (true) {
            internetAvailable = !internetAvailable
            onMain { onChange(internetAvailable) }
            delay(1000)
        }
    }

    private fun onChange(isInternetAvailable: Boolean) = when (isInternetAvailable) {
        false -> navigation.activate(DefaultRootComponent.SlotConfig.InternetBanner)
        true -> navigation.dismiss()
    }
}