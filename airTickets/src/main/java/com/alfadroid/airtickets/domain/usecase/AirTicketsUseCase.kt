package com.alfadroid.airtickets.domain.usecase

import com.alfadroid.airtickets.domain.repository.Offer
import com.alfadroid.airtickets.domain.repository.OffersRepository

class AirTicketsUseCase(val repository: OffersRepository) {

    suspend fun getOffers(): List<Offer> = repository.getOffers()
}
