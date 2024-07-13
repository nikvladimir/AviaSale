package com.alfadroid.airtickets.data.repository

import com.alfadroid.airtickets.data.dto.toOffer
import com.alfadroid.airtickets.domain.repository.Offer
import com.alfadroid.airtickets.domain.repository.OffersRepository
import com.alfadroid.network.AirTicketsApi

class OffersRepositoryImpl(private val api: AirTicketsApi) : OffersRepository {

    override suspend fun getOffers(): List<Offer> =
        api.getOffers(OFFERS_URL).offers.map {
            it.toOffer()
        }

    companion object {

        private const val OFFERS_URL = "uc?id=1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav&export=download"
    }
}
