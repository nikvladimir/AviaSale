package com.alfadroid.departure.domain.usecase

import com.alfadroid.departure.domain.models.AirCompany
import com.alfadroid.departure.domain.models.Ticket
import com.alfadroid.departure.domain.repository.TicketsRepository

class DepartureUseCase(val repository: TicketsRepository) {

    suspend fun getTickets(): Map<AirCompany, List<Ticket>> =
        repository.getTickets().groupBy { it.company }
}