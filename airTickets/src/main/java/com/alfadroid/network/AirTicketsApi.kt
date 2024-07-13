package com.alfadroid.network

import com.alfadroid.airtickets.data.dto.OfferListResponse
import com.alfadroid.departure.data.dto.TicketsResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface AirTicketsApi {
    @GET
    suspend fun getOffers(
        @Url url: String
    ): OfferListResponse

    @GET
    suspend fun getTickets(
        @Url url: String
    ): TicketsResponse
}
