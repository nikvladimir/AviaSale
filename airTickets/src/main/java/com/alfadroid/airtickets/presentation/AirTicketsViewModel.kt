package com.alfadroid.airtickets.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alfadroid.airtickets.R
import com.alfadroid.airtickets.domain.usecase.AirTicketsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AirTicketsViewModel(useCase: AirTicketsUseCase) : ViewModel() {

    val items = listOf(
        Item(R.drawable.ic_launcher_foreground, "View Model 1"),
        Item(R.drawable.ic_launcher_foreground, "Item 2"),
        Item(R.drawable.ic_launcher_foreground, "Item 3"),
        Item(R.drawable.ic_launcher_foreground, "Item 4"),
        Item(R.drawable.ic_launcher_foreground, "Item 5")
    )
    val screenState = MutableStateFlow<AirTicketsScreenState>(AirTicketsScreenState.Loading)

    init {
        viewModelScope.launch {
            useCase.getOffers()
            screenState.emit(AirTicketsScreenState.Ready(
                departure = "departure",
                destination = "destination",
                offers = items
            ))
        }
    }
}

sealed interface AirTicketsScreenState {

    object Loading : AirTicketsScreenState

    data class Error(
        val throwable: Throwable
    ) : AirTicketsScreenState

    data class Ready(
        val departure: String,
        val destination: String,
        val offers: List<Item>,
    ) : AirTicketsScreenState
}

