package com.alfadroid.airtickets.data.retrofit

data class OffersDTO(
    val id: String,
    val title: String,
    val town: String,
    val price: Price,
)

data class Price(
    val value: Int
)