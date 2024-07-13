package com.alfadroid.airtickets.domain.usecase

import com.alfadroid.airtickets.domain.repository.AirTicketsRepository
import com.alfadroid.airtickets.domain.repository.Offer

class AirTicketsUseCase(val repository: AirTicketsRepository) {

    suspend fun getOffers(): List<Offer> = repository.getOffers()
}