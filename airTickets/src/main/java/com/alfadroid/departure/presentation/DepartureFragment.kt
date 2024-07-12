package com.alfadroid.departure.presentation

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alfadroid.airtickets.R
import com.alfadroid.airtickets.databinding.FragmentDepartureBinding
import com.alfadroid.destination.presentation.DestinationBottomSheetFragment
import com.alfadroid.viewAllTickets.presentation.ViewAllTicketsFragment
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DepartureFragment : Fragment() {
    private val binding: FragmentDepartureBinding by viewBinding(FragmentDepartureBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val date = LocalDate.now()
        updateDateDayInViews(date)
        binding.llDepartureDate.setOnClickListener { showDatePickedDialog(date, "back") }
        binding.llReturnDate.setOnClickListener { showDatePickedDialog(date, direction = "back") }
        return FragmentDepartureBinding.inflate(layoutInflater).root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBackArrow.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.ivSwapDepartureDestination.setOnClickListener {
            val tmpDepartureText = binding.tvDeparture.text
            binding.tvDeparture.text = binding.tvDestination.text
            binding.tvDestination.text = tmpDepartureText
        }

        binding.ivClearDestination.setOnClickListener { binding.tvDestination.text = "" }

        binding.btnShowAllTickets.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.hostAirTicketsFragment, ViewAllTicketsFragment.newInstance())
                    .addToBackStack(null).commit()
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        DestinationBottomSheetFragment().show(parentFragmentManager, "BottomSheetFragment")
    }

    private fun showDatePickedDialog(date: LocalDate, direction: String) {
        val datePickerDialog = DatePickerDialog(
            requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                if (direction == "forth") {
                    updateDateDayInViews(LocalDate.of(selectedYear, selectedMonth + 1, selectedDay))
                }
            }, date.year, date.monthValue, date.dayOfMonth
        )
        datePickerDialog.show()
    }

    private fun updateDateDayInViews(date: LocalDate) {
        val datePattern = DateTimeFormatter.ofPattern("dd MMMM")
        val dayOfWeekPattern = DateTimeFormatter.ofPattern("EE")
        binding.tvDepartureDate.text = datePattern.format(date)
        binding.tvDepartureDay.text = dayOfWeekPattern.format(date)
    }

    companion object {
        fun newInstance(): DepartureFragment = DepartureFragment()
    }
}