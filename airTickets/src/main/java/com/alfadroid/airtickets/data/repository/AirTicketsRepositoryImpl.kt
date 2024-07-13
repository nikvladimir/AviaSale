package com.alfadroid.airtickets.data.repository

import com.alfadroid.airtickets.data.dto.toOffer
import com.alfadroid.airtickets.data.retrofit.OffersApi
import com.alfadroid.airtickets.domain.repository.AirTicketsRepository
import com.alfadroid.airtickets.domain.repository.Offer

class AirTicketsRepositoryImpl(private val api: OffersApi) : AirTicketsRepository {

    override suspend fun getOffers(): List<Offer> =
        api.downloadFile(OFFERS_URL).offers.map {
            it.toOffer()
        }

    companion object {

        private const val OFFERS_URL = "uc?id=1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav&export=download"
    }
}
