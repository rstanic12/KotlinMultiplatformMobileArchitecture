package com.kotlin.multiplatform.features.home

import com.kotlin.multiplatform.core.presentation.navigation.Navigation
import com.kotlin.multiplatform.core.presentation.presenter.RenderablePresenter

class HomePresenter(
    private val navigation: Navigation,
    private val tabNavigation: TabNavigation,
) : RenderablePresenter<
    HomeViewState,
    HomeViewModel,
    HomeViewStore>(
    HomeViewStore()
) {
    override suspend fun onBind() {

    }
}