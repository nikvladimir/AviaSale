package com.alfadroid.airtickets.presentation

import com.alfadroid.airtickets.data.repository.AirTicketsRepositoryImpl
import com.alfadroid.airtickets.data.retrofit.OffersApi
import com.alfadroid.airtickets.domain.repository.AirTicketsRepository
import com.alfadroid.airtickets.domain.usecase.AirTicketsUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object AirTicketsModule {
    val BASEURL = "https://drive.usercontent.google.com/u/0/"

    val airTicketsModule = module {
        single { provideRetrofit() }
        single { provideApi(retrofit = get()) }
        single<AirTicketsRepository> { AirTicketsRepositoryImpl(api = get()) }
        single { AirTicketsUseCase(repository = get()) }
        viewModel { AirTicketsViewModel(useCase = get()) }
    }

    private fun provideRetrofit() =
        Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private fun provideApi(retrofit: Retrofit) =
        retrofit.create<OffersApi>()
}
