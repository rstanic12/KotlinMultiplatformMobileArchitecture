package com.kotlin.multiplatform.features.home

import com.kotlin.multiplatform.VehicleLocationSubscription
import com.kotlin.multiplatform.core.presentation.BaseViewState

sealed class HomeViewState : BaseViewState {
    data class VehicleLocationChanged(val coordinate: VehicleLocationSubscription.Coordinate) : HomeViewState()
}
