package com.alfadroid.departure.data.repository

import com.alfadroid.airtickets.data.dto.toOffer
import com.alfadroid.departure.data.dto.toTicket
import com.alfadroid.departure.domain.repository.Ticket
import com.alfadroid.network.AirTicketsApi
import com.alfadroid.departure.domain.repository.TicketsRepository

class TicketsRepositoryImpl(private val api: AirTicketsApi) : TicketsRepository {

    override suspend fun getTickets(): List<Ticket> =
        api.getTickets(TICKETS_URL).tickets.map {
            it.toTicket()
        }

    companion object {

        private const val TICKETS_URL = "uc?export=download&id=1HogOsz4hWkRwco4kud3isZHFQLUAwNBA"
    }
}
