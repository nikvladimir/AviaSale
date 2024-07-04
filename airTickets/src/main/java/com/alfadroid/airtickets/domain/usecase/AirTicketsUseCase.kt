package com.alfadroid.airtickets.domain.usecase

import com.alfadroid.airtickets.data.dto.Offers
import com.alfadroid.airtickets.data.dto.OffersDTO
import com.alfadroid.airtickets.domain.repository.AirTicketsRepository

class AirTicketsUseCase(val repository: AirTicketsRepository) {

    suspend fun getOffers(): List<OffersDTO> = repository.getOffers()
}