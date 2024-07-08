package com.alfadroid.airtickets.data.repository

import com.alfadroid.airtickets.data.dto.OffersDTO
import com.alfadroid.airtickets.data.retrofit.OffersApi
import com.alfadroid.airtickets.domain.repository.AirTicketsRepository
import com.alfadroid.common.Constants.Companion.URL_AIR_TICKETS_REPOSITORY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class AirTicketsRepositoryImpl : AirTicketsRepository {

    private val retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val api by lazy {
        retrofit.create<OffersApi>()
    }

    override suspend fun getOffers(): List<OffersDTO> =
        api.downloadFile(URL_AIR_TICKETS_REPOSITORY).offers
}
