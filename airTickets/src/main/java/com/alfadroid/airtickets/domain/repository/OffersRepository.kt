package com.alfadroid.airtickets.domain.repository

interface OffersRepository {
    suspend fun getOffers(): List<Offer>
}
