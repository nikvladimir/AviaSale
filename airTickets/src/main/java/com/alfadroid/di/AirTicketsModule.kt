package com.alfadroid.di

import com.alfadroid.airtickets.data.repository.OffersRepositoryImpl
import com.alfadroid.airtickets.domain.repository.OffersRepository
import com.alfadroid.airtickets.domain.usecase.AirTicketsUseCase
import com.alfadroid.network.AirTicketsApi
import com.alfadroid.departure.domain.usecase.DepartureUseCase
import com.alfadroid.airtickets.presentation.AirTicketsViewModel
import com.alfadroid.departure.data.repository.TicketsRepositoryImpl
import com.alfadroid.departure.domain.repository.TicketsRepository
import com.alfadroid.departure.presentation.DepartureViewModel
import com.example.common.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object AirTicketsModule {

    val networkModule = module {
        single { provideClient() }
        single { provideRetrofit(client = get()) }
        single { provideApi(retrofit = get()) }
    }

    val airTicketsModule = module {
        single<OffersRepository> { OffersRepositoryImpl(api = get()) }
        single { AirTicketsUseCase(repository = get()) }
        viewModel { AirTicketsViewModel(useCase = get()) }
    }

    val departureModule = module {
        single<TicketsRepository> { TicketsRepositoryImpl(api = get()) }
        single { DepartureUseCase(repository = get()) }
        viewModel { DepartureViewModel(useCase = get()) }
    }


    private fun provideClient(): OkHttpClient {
        val intercepter = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(intercepter)
        }.build()

        return client
    }


    private fun provideRetrofit(client: OkHttpClient) =
        Retrofit.Builder()
            .client(client)
            .baseUrl(Constants.AIR_TICKETS_BASEURL_OFFERS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private fun provideApi(retrofit: Retrofit) =
        retrofit.create<AirTicketsApi>()
}
