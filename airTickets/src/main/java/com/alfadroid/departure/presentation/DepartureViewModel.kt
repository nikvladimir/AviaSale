package com.alfadroid.departure.presentation


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alfadroid.airtickets.R
import com.alfadroid.airtickets.domain.Image
import com.alfadroid.departure.domain.usecase.DepartureUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import com.alfadroid.common.R as RCommon

class DepartureViewModel(useCase: DepartureUseCase) : ViewModel() {

    val screenState = MutableStateFlow<DepartureScreenState>(DepartureScreenState.Loading)

    init {
        viewModelScope.launch {
            val tickets = useCase.getTickets()
            screenState.emit(
                DepartureScreenState.Ready(
                   tickets = tickets
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
