package com.alfadroid.departure.data.dto

import com.alfadroid.departure.domain.repository.Ticket
import com.google.gson.annotations.SerializedName

data class TicketResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("badge")
    val badge: String?,
    @SerializedName("price")
    val price: Price,
    @SerializedName("provider_name")
    val providerName: String,
    @SerializedName("company")
    val company: String,
    @SerializedName("departure")
    val departure: AirportDTO,
    @SerializedName("arrival")
    val arrival: AirportDTO,
    @SerializedName("has_transfer")
    val hasTransfer: Boolean,
    @SerializedName("has_visa_transfer")
    val hasVisaTransfer: Boolean,
    @SerializedName("luggage")
    val luggage: LuggageDTO,
    @SerializedName("hand_luggage")
    val handLuggage: HandLuggageDTO,
    @SerializedName("is_returnable")
    val isReturnable: Boolean,
    @SerializedName("is_exchangable")
    val isExchangable: Boolean,
) {
    data class Price(
        @SerializedName("value")
        val value: Int,
    )

    data class AirportDTO(
        @SerializedName("town")
        val town: String,
        @SerializedName("date")
        val date: String,
        @SerializedName("airport")
        val airport: String,
    )

    data class LuggageDTO(
        @SerializedName("has_luggage")
        val hasLuggage: Boolean,
        @SerializedName("price")
        val price: Price?,
    )

    data class HandLuggageDTO(
        @SerializedName("has_hand_luggage")
        val hasHandLuggage: Boolean,
        @SerializedName("size")
        val size: String?,
    )
}

fun TicketResponse.toTicket() = Ticket(
    id = id,
    badge = badge,
    price = price.value,
    providerName = providerName,
    company = company,
    departure = departure.toAirport(),
    arrival = arrival.toAirport(),
    hasTransfer = hasTransfer,
    hasVisaTransfer = hasVisaTransfer,
    luggage = luggage.toLuggage(),
    handLuggage = handLuggage.toHandLuggage(),
    isReturnable = isReturnable,
    isExchangable = isExchangable,

    )

private fun TicketResponse.AirportDTO.toAirport() = Ticket.Airport(
    town = town,
    date = date,
    airport = airport
)

private fun TicketResponse.LuggageDTO.toLuggage() = Ticket.Luggage(
    hasLuggage = hasLuggage,
    price = price?.value
)

private fun TicketResponse.HandLuggageDTO.toHandLuggage() = Ticket.HandLuggage(
    hasHandLuggage = hasHandLuggage,
    size = size
)


