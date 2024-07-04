package com.alfadroid.airtickets.data.retrofit

import com.alfadroid.common.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

//url = https://drive.usercontent.google.com/u/0/uc?id=1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav&export=download
    private val retrofit: Retrofit = Retrofit
        .Builder()
        .baseUrl(Constants.offersBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: OffersAPIInterface = retrofit.create(OffersAPIInterface::class.java)
}