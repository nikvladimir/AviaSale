package com.alfadroid.airtickets.presentation

import com.alfadroid.airtickets.data.repository.AirTicketsRepositoryImpl
import com.alfadroid.airtickets.domain.repository.AirTicketsRepository
import com.alfadroid.airtickets.domain.usecase.AirTicketsUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AirTicketsModule {

    val airTicketsModule = module {
        single<AirTicketsRepository> { AirTicketsRepositoryImpl() }
        single { AirTicketsUseCase( repository = get()) }
        viewModel { AirTicketsViewModel(useCase = get())}
    }
}