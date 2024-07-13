package com.alfadroid.airtickets.data.dto

import com.alfadroid.airtickets.domain.Image
import com.alfadroid.airtickets.domain.repository.Offer
import com.google.gson.annotations.SerializedName

data class OfferResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("town") val town: String,
    @SerializedName("price") val price: PriceResponse,
) {
    data class PriceResponse(
        @SerializedName("value") val value: Int
    )
}

fun OfferResponse.toOffer() = Offer(
    id = id,
    image = when (id) {
        1 -> Image.DIE_ANTWOORD
        2 -> Image.SOCRAT_LERA
        3 -> Image.LAMPABIKT
        else -> null
    },
    title = title,
    town = town,
    price = price.value
)
