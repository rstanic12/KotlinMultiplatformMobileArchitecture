package com.kotlin.multiplatform.usecases

import com.apollographql.apollo3.api.ApolloResponse
import com.kotlin.multiplatform.VehicleLocationSubscription
import com.kotlin.multiplatform.repositories.VehicleLocationRepository
import kotlinx.coroutines.flow.Flow

class SubscribeToVehicleLocation(private val vehicleLocationRepository: VehicleLocationRepository) {
    suspend operator fun invoke(): Flow<ApolloResponse<VehicleLocationSubscription.Data>> {
        return vehicleLocationRepository.subscribe()
    }
}