package com.alfadroid.departure.domain.repository

interface TicketsRepository {
    suspend fun getTickets(): List<Ticket>
}
