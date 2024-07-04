package com.alfadroid.airtickets.presentation

import androidx.lifecycle.ViewModel
import com.alfadroid.airtickets.domain.usecase.AirTicketsUseCase


import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AirTicketsViewModel(useCase: AirTicketsUseCase) : ViewModel() {

    init {
        viewModelScope.launch {
            useCase.getOffers()

        }
    }
}