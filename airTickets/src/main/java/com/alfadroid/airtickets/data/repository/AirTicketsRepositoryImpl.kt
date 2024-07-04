package com.alfadroid.airtickets.data.repository

import com.alfadroid.airtickets.data.dto.Offers
import com.alfadroid.airtickets.data.dto.OffersDTO
import com.alfadroid.airtickets.data.dto.Price
import com.alfadroid.airtickets.domain.repository.AirTicketsRepository

class AirTicketsRepositoryImpl : AirTicketsRepository {

    override suspend fun getOffers(): List<OffersDTO> =
        listOf(
            OffersDTO(1, "Die Antwoord", "Будапешт", Price(5000)),
            OffersDTO(2, "Socrat&Lera", "Санкт-Петербург", Price(1999)),
            OffersDTO(3, "Лампабикт", "Москва", Price(2390)),
            )
}