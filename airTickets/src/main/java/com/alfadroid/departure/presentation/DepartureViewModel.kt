package com.alfadroid.departure.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alfadroid.departure.domain.models.AirCompany
import com.alfadroid.departure.domain.models.TicketItem
import com.alfadroid.departure.domain.models.Ticket
import com.alfadroid.departure.domain.usecase.DepartureUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class DepartureViewModel(useCase: DepartureUseCase) : ViewModel() {

    val screenState = MutableStateFlow<DepartureScreenState>(DepartureScreenState.Loading)

    init {
        viewModelScope.launch {
            val tickets = useCase.getTickets().toTicketItem()
            screenState.emit(
                DepartureScreenState.Ready(
                    tickets = tickets
                )
            )
        }
    }
}

private fun Map<AirCompany, List<Ticket>>.toTicketItem() =
    this.mapValues {
        TicketItem(
            company = it.key.title,
            companyImageRes = it.key.toImage(),
            price = it.value.sortedByDescending { it.price }.first().price.formatPrice(),
            timeRanges = it.value.map { extractHoursAndMinutes(it.departure.date) }
                .joinToString(", ")
        )
    }.map { it.value }


private fun Int.formatPrice(): String =
    String.format(Locale.ROOT, "%,d", this).replace(',', ' ') + " ₽"

private fun extractHoursAndMinutes(dateTime: String): String {
    val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val outputFormat = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())

    val localDateTime: LocalDateTime = try {
        LocalDateTime.parse(dateTime, inputFormat)
    } catch (e: Exception) {
        return "Invalid date"
    }

    return localDateTime.format(outputFormat)
}


private fun AirCompany.toImage() =
    when (this) {
        AirCompany.AEROFLOT -> com.alfadroid.common.R.drawable.icon_circle_blue
        AirCompany.URAL -> com.alfadroid.common.R.drawable.icon_circle_red
        else -> com.alfadroid.common.R.drawable.icon_circle_white
    }

