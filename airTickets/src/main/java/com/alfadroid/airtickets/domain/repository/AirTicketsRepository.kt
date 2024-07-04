package com.alfadroid.airtickets.domain.repository

import com.alfadroid.airtickets.data.dto.Offers
import com.alfadroid.airtickets.data.dto.OffersDTO

interface AirTicketsRepository {

    suspend fun getOffers(): List<OffersDTO>
}