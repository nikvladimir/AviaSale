package com.alfadroid.airtickets.data.repository

import com.alfadroid.airtickets.data.dto.OffersDTO
import com.alfadroid.airtickets.data.retrofit.OffersApi
import com.alfadroid.airtickets.domain.repository.AirTicketsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class AirTicketsRepositoryImpl : AirTicketsRepository {
    val URL_AIR_TICKETS_REPOSITORY = "https://drive.usercontent.google.com/u/0/uc?id=1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav&export=download"

//    private val retrofit by lazy {
//        Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//    private val api by lazy {
//        retrofit.create<OffersApi>()
//    }

    private val retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val api =
        retrofit.create<OffersApi>()


    override suspend fun getOffers(): List<OffersDTO> =
        api.downloadFile(URL_AIR_TICKETS_REPOSITORY).offers
}
