package com.alfadroid.airtickets.data.retrofit

import com.alfadroid.airtickets.data.dto.OfferListResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface OffersApi {
    @GET
    suspend fun downloadFile(
        @Url url: String
    ): OfferListResponse
}
