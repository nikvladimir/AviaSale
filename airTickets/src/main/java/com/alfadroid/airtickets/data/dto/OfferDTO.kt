package com.alfadroid.airtickets.data.dto

data class OffersDTO(
    val id: Int,
    val title: String,
    val town: String,
    val price: Price,
)

data class Price(
    val value: Int
)