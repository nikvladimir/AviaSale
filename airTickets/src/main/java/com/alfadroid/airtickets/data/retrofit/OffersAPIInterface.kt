package com.alfadroid.airtickets.data.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OffersAPIInterface {
    @GET("u/0/uc")
    suspend fun queryAPI(
        @Query("id") id: String,
        @Query("export") export: String
    ): Response<Offers>
}