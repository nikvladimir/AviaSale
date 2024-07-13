package com.alfadroid.departure.domain.models

data class Ticket(
    val id: Long,
    val badge: String?,
    val price: Int,
    val providerName: String,
    val company: AirCompany,
    val departure: Airport,
    val arrival: Airport,
    val hasTransfer: Boolean,
    val hasVisaTransfer: Boolean,
    val luggage: Luggage,
    val handLuggage: HandLuggage,
    val isReturnable: Boolean,
    val isExchangable: Boolean,
) {
    data class Airport(
        val town: String,
        val date: String,
        val airport: String,
    )

    data class Luggage(
        val hasLuggage: Boolean,
        val price: Int?,
    )

    data class HandLuggage(
        val hasHandLuggage: Boolean,
        val size: String?,
    )
}