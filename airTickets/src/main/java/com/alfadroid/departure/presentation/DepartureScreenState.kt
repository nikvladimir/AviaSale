package com.alfadroid.departure.presentation

import com.alfadroid.departure.domain.models.TicketItem

sealed interface DepartureScreenState {

    object Loading : DepartureScreenState

    data class Error(
        val throwable: Throwable
    ) : DepartureScreenState

    data class Ready(
        val tickets: List<TicketItem>,
    ) : DepartureScreenState
}
