package com.alfadroid.airtickets.domain.repository

interface AirTicketsRepository {
    suspend fun getOffers(): List<Offer>
}