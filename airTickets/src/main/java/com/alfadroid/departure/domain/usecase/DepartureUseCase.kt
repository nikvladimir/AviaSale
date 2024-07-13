package com.alfadroid.departure.domain.usecase

import com.alfadroid.departure.domain.models.TicketItem
import com.alfadroid.departure.domain.repository.Ticket
import com.alfadroid.departure.domain.repository.TicketsRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DepartureUseCase(val repository: TicketsRepository) {

    suspend fun getTickets(): List<TicketItem> {
        repository.getTickets()
        val tickets = repository.getTickets().groupBy { it.company }
        return  tickets.mapValues { TicketItem(
            company = it.key,
            price = it.value.sortedByDescending { it.price }.first().price.formatPrice(),
            timeRanges = it.value.map { extractHoursAndMinutes(it.departure.date) }.toString()
        ) }.map { it.value }
    }


    private fun Int.formatPrice(): String =
        String.format(Locale.ROOT, "%,d", this).replace(',', ' ') + " ₽"


    private fun extractHoursAndMinutes(dateTime: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        val date: Date = inputFormat.parse(dateTime) ?: return "Invalid date"
        return outputFormat.format(date)
    }
}

