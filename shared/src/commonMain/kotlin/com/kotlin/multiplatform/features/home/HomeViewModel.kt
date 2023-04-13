package com.kotlin.multiplatform.features.home

import com.kotlin.multiplatform.VehicleLocationSubscription
import com.kotlin.multiplatform.core.presentation.BaseViewModel

data class HomeViewModel(
    val coordinate: VehicleLocationSubscription.Coordinate = VehicleLocationSubscription.Coordinate(0.0, 0.0)
) : BaseViewModel