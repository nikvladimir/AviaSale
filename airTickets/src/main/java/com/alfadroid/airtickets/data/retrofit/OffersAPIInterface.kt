package com.alfadroid.airtickets.data.retrofit

import com.alfadroid.airtickets.data.dto.Offers
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OffersAPIInterface {
    @GET("u/0/uc")
    suspend fun downloadFile(
        @Query("id") id: String,
        @Query("export") export: String
    ): Response<Offers>
}