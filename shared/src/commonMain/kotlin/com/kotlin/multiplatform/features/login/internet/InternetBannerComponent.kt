package com.kotlin.multiplatform.features.login.internet

import com.arkivanov.decompose.ComponentContext
import com.kotlin.multiplatform.core.presentation.ActionDispatcher
import com.kotlin.multiplatform.core.presentation.navigation.SlotNavigation

interface InternetBannerComponent

class DefaultInternetBannerComponent(
    componentContext: ComponentContext,
    navigation: SlotNavigation,
    actionDispatcher: ActionDispatcher = ActionDispatcher(),
) : InternetBannerComponent, ComponentContext by componentContext {

    private val internetBannerPresenter = InternetBannerPresenter(navigation)

    init {
        actionDispatcher.bind(internetBannerPresenter)
        instanceKeeper.put(internetBannerPresenter, internetBannerPresenter)
    }
}

class PreviewInternetBannerComponent : InternetBannerComponent

fun internetBannerComponent(componentContext: ComponentContext, navigation: SlotNavigation): InternetBannerComponent =
    DefaultInternetBannerComponent(componentContext, navigation)
