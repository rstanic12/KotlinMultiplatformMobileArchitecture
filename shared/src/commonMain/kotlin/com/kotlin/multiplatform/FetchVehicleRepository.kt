package com.kotlin.multiplatform

import com.apollographql.apollo3.ApolloClient

interface FetchVehicleRepository {
    suspend operator fun invoke(id: Int)
}

class ApolloFetchVehicleRepository: FetchVehicleRepository {
    val client = ApolloClient.Builder().serverUrl("https://oyster-app-qlrrz.ondigitalocean.app/graphql").build()

    override suspend fun invoke(id: Int) {
        val query = VehicleQuery(id)
        val response = client.query(query).execute()
        print("RESPONSE" + response)
    }
}