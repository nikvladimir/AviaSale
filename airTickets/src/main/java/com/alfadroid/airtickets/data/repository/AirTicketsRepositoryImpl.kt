package com.alfadroid.airtickets.data.repository

import com.alfadroid.airtickets.data.dto.OffersDTO
import com.alfadroid.airtickets.data.retrofit.OffersApi
import com.alfadroid.airtickets.domain.repository.AirTicketsRepository

class AirTicketsRepositoryImpl(private val api: OffersApi) : AirTicketsRepository {
    override suspend fun getOffers(): List<OffersDTO> =
        api.downloadFile("uc?id=1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav&export=download").offers
}
