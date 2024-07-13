package com.alfadroid.airtickets.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alfadroid.airtickets.R
import com.alfadroid.airtickets.domain.Image
import com.alfadroid.airtickets.domain.usecase.AirTicketsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import com.alfadroid.common.R as RCommon

class AirTicketsViewModel(useCase: AirTicketsUseCase) : ViewModel() {

    val screenState = MutableStateFlow<AirTicketsScreenState>(AirTicketsScreenState.Loading)

    init {
        viewModelScope.launch {
            val response = useCase.getOffers().map {
                Item(
                    id = it.town,
                    imageResId = it.image.toImageRes(),
                    title = it.title,
                    town = it.town,
                    price = formattedNNumber(it.price)
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

    private fun Image?.toImageRes(): Int =
        when (this) {
            Image.DIE_ANTWOORD -> R.drawable.image_1
            Image.SOCRAT_LERA -> R.drawable.image_2
            Image.LAMPABIKT -> R.drawable.image_3
            else -> RCommon.drawable.icon_hot_tickets
        }
}

private fun formattedNNumber(number: Int): String {
    return String.format(Locale.ROOT, "%,d", number).replace(',', ' ') + " ₽"
}
