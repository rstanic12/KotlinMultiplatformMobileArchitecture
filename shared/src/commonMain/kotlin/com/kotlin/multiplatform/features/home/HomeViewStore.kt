package com.kotlin.multiplatform.features.home

import com.kotlin.multiplatform.core.presentation.viewstore.RenderableViewStore

class HomeViewStore : RenderableViewStore<
    HomeViewState,
    HomeViewModel>(
    HomeViewModel()
) {
    override fun reduce(viewState: HomeViewState) = when (viewState) {
        is HomeViewState.VehicleLocationChanged -> viewModel.copy(coordinate = viewState.coordinate)
    }.apply { render(this) }
}
