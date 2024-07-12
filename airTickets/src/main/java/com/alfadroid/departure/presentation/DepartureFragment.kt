package com.alfadroid.departure.presentation

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alfadroid.airtickets.databinding.FragmentDepartureBinding
import com.alfadroid.destination.presentation.DestinationBottomSheetFragment
import java.util.Calendar

class DepartureFragment : Fragment() {

    private lateinit var binding: FragmentDepartureBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDepartureBinding.inflate(layoutInflater)

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day_month = calendar.get(Calendar.DAY_OF_MONTH)

        updateDateDayInViews(year, month, day_month)

        binding.llDepartureDate.setOnClickListener {
            showDatePickedDialog(year, month, day_month)
        }
        binding.llReturnDate.setOnClickListener {
            showDatePickedDialog(year, month, day_month, direction = "back")
        }

        return binding.root
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

    }

    override fun onDetach() {
        super.onDetach()
        DestinationBottomSheetFragment().show(parentFragmentManager, "BottomSheetFragment")
    }

    private fun showDatePickedDialog(
        year: Int, month: Int, dayMonth: Int, direction: String = "forth"
    ) {
        val datePickerDialog = DatePickerDialog(
            requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                if (direction == "forth") {
                    updateDateDayInViews(selectedYear, selectedMonth, selectedDay)
                }
            }, year, month, dayMonth
        )
        datePickerDialog.show()
    }

    private fun updateDateDayInViews(year: Int = 0, month: Int = 0, day: Int = 0) {
        binding.tvDepartureDate.text = String.format("%02d %s", day, getMonthString(month + 1))
        binding.tvDepartureDay.text = String.format(", %s", getDayOfWeekString(year, month, day))
    }

    private fun getMonthString(month: Int): String {
        return when (month) {
            1 -> getString(com.alfadroid.common.R.string.january)
            2 -> getString(com.alfadroid.common.R.string.february)
            3 -> getString(com.alfadroid.common.R.string.march)
            4 -> getString(com.alfadroid.common.R.string.april)
            5 -> getString(com.alfadroid.common.R.string.may)
            6 -> getString(com.alfadroid.common.R.string.june)
            7 -> getString(com.alfadroid.common.R.string.july)
            8 -> getString(com.alfadroid.common.R.string.august)
            9 -> getString(com.alfadroid.common.R.string.september)
            10 -> getString(com.alfadroid.common.R.string.october)
            11 -> getString(com.alfadroid.common.R.string.november)
            12 -> getString(com.alfadroid.common.R.string.december)
            else -> ""
        }
    }

    private fun getDayOfWeekString(year: Int, month: Int, day: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        return when (calendar.get(Calendar.DAY_OF_WEEK)) {
            Calendar.MONDAY -> getString(com.alfadroid.common.R.string.monday)
            Calendar.SUNDAY -> getString(com.alfadroid.common.R.string.tuesday)
            Calendar.TUESDAY -> getString(com.alfadroid.common.R.string.wednesday)
            Calendar.WEDNESDAY -> getString(com.alfadroid.common.R.string.thursday)
            Calendar.THURSDAY -> getString(com.alfadroid.common.R.string.friday)
            Calendar.FRIDAY -> getString(com.alfadroid.common.R.string.saturday)
            Calendar.SATURDAY -> getString(com.alfadroid.common.R.string.sunday)
            else -> ""
        }
    }
}