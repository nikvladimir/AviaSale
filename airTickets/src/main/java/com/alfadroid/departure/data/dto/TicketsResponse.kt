package com.alfadroid.departure.data.dto

import com.google.gson.annotations.SerializedName

data class TicketsResponse(
    @SerializedName("tickets")
    val tickets: List<TicketResponse>
)
