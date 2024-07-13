package com.alfadroid.departure.domain.repository

import com.alfadroid.departure.domain.models.Ticket

interface TicketsRepository {
    suspend fun getTickets(): List<Ticket>
}
