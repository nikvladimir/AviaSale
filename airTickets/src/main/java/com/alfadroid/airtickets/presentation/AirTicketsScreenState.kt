package com.alfadroid.airtickets.presentation

sealed interface AirTicketsScreenState {

    object Loading : AirTicketsScreenState

    data class Error(
        val throwable: Throwable
    ) : AirTicketsScreenState

    data class Ready(
        val departure: String,
        val destination: String,
        val offers: List<Item>,
    ) : AirTicketsScreenState
}