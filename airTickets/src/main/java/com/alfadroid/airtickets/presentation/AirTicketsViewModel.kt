package com.alfadroid.airtickets.presentation


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alfadroid.airtickets.R
import com.alfadroid.airtickets.domain.usecase.AirTicketsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AirTicketsViewModel(useCase: AirTicketsUseCase) : ViewModel() {

    val screenState = MutableStateFlow<AirTicketsScreenState>(AirTicketsScreenState.Loading)

    init {
        viewModelScope.launch {
            val responce = useCase.getOffers().map {
                Item(
                    text = it.title,
                    imageResId = R.drawable.icon_launcher_foreground
                )
            }
            Log.d("CATCAT", "$responce")
            screenState.emit(
                AirTicketsScreenState.Ready(
                    departure = "departure",
                    destination = "destination",
                    offers = responce
                )
            )
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

