package com.kotlin.multiplatform.features.home

import com.kotlin.multiplatform.core.presentation.viewstore.RenderableViewStore

class HomeViewStore : RenderableViewStore<
    HomeViewState,
    HomeViewModel>(
    HomeViewModel()
) {
    override fun reduce(viewState: HomeViewState): HomeViewModel {
        return HomeViewModel()
    }
}
