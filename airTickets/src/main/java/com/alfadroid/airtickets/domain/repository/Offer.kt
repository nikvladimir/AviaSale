package com.alfadroid.airtickets.domain.repository

import com.alfadroid.airtickets.domain.Image

data class Offer(
    val id: Int,
    val image: Image?,
    val title: String,
    val town: String,
    val price: Int,
)