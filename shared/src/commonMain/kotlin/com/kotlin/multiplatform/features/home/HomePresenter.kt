package com.kotlin.multiplatform.features.home

import com.kotlin.multiplatform.core.presentation.navigation.Navigation
import com.kotlin.multiplatform.core.presentation.presenter.RenderablePresenter
import com.kotlin.multiplatform.repositories.ApolloVehicleLocationRepository
import com.kotlin.multiplatform.usecases.SubscribeToVehicleLocation
import kotlinx.coroutines.flow.collectLatest

class HomePresenter(
    private val navigation: Navigation,
    private val tabNavigation: TabNavigation,
) : RenderablePresenter<
    HomeViewState,
    HomeViewModel,
    HomeViewStore>(
    HomeViewStore()
) {
    private val vehicleLocationRepository = ApolloVehicleLocationRepository()
    private val subscribeToVehicleLocation = SubscribeToVehicleLocation(vehicleLocationRepository)

    override suspend fun onBind() {
        subscribeToVehicleLocation().collectLatest {
            render(HomeViewState.VehicleLocationChanged(it.data?.coordinate!!))
        }
    }
}