package com.alfadroid.airtickets.presentation


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
            val response = useCase.getOffers().map {
                Item(
                    imageResId = getImageRes(it.id),
                    title = it.title,
                    town = it.town,
                    price = it.price.value.toString() + " ₽"

                )
            }
            screenState.emit(
                AirTicketsScreenState.Ready(
                    departure = "departure",
                    destination = "destination",
                    offers = response
                )
            )
        }
    }

    private fun getImageRes(id: Int): Int =
        when (id) {
            Images.DIE_ANTWOORD.id -> {
                R.drawable.img_1
            }

            Images.SOCRAT_LERA.id -> {
                R.drawable.img_2
            }

            Images.LAMPABIKT.id -> {
                R.drawable.img_3
            }

            else -> {
                R.drawable.icon_hot_tickets
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

enum class Images(val id: Int) {
    DIE_ANTWOORD(id = 1),
    SOCRAT_LERA(id = 2),
    LAMPABIKT(id = 3),
}

