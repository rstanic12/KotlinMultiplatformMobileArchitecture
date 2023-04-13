package com.kotlin.multiplatform.repositories

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.kotlin.multiplatform.VehicleLocationSubscription
import kotlinx.coroutines.flow.Flow

interface VehicleLocationRepository {
    suspend fun subscribe(): Flow<ApolloResponse<VehicleLocationSubscription.Data>>
}

class ApolloVehicleLocationRepository: VehicleLocationRepository {
    private val client = ApolloClient.Builder().serverUrl("https://c9a0-78-0-83-0.ngrok-free.app/graphql").build()

    override suspend fun subscribe(): Flow<ApolloResponse<VehicleLocationSubscription.Data>> {
        val subscription = VehicleLocationSubscription()
        return client.subscription(subscription).toFlow()
    }
}