package com.alfadroid.airtickets.data.dto

import com.google.gson.annotations.SerializedName

data class OfferListResponse(
    @SerializedName("offers")
    val offers: List<OfferResponse>
)
