package com.alfadroid.departure.presentation

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alfadroid.airtickets.R
import com.alfadroid.airtickets.databinding.FragmentDepartureBinding
import com.alfadroid.viewAllTickets.presentation.ViewAllTicketsFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class DepartureFragment : Fragment() {
    private val binding: FragmentDepartureBinding by viewBinding(FragmentDepartureBinding::bind)
    private val departureAdapter = DepartureAdapter()
    private val viewModel by viewModel<DepartureViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return FragmentDepartureBinding.inflate(layoutInflater).root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val date = LocalDate.now()
        updateDateDayInViews(date)

        binding.recyclerViewDepartureTickets.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = departureAdapter
        }

        binding.llDepartureDate.setOnClickListener { showDatePickedDialog(date, "back") }
        binding.llReturnDate.setOnClickListener { showDatePickedDialog(date, direction = "back") }

        with(binding) {
            ivBackArrow.setOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }

            ivSwapDepartureDestination.setOnClickListener {
                val tmpDepartureText = binding.tvDeparture.text
                binding.tvDeparture.text = binding.tvDestination.text
                binding.tvDestination.text = tmpDepartureText
            }

            ivClearDestination.setOnClickListener { binding.tvDestination.text = "" }

            binding.btnShowAllTickets.setOnClickListener {
                parentFragmentManager.commit {
                    replace(R.id.hostAirTicketsFragment, ViewAllTicketsFragment.newInstance())
                    addToBackStack(null)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.screenState.collect { state ->
                    when (state) {
                        is DepartureScreenState.Loading -> {
                            renderLoading(state)
                        } //todo
                        is DepartureScreenState.Error -> {} //todo
                        is DepartureScreenState.Ready -> {
                            render(state)
                        }
                    }
                }
            }
        }
    }

    private fun showDatePickedDialog(date: LocalDate, direction: String) {
        val datePickerDialog = DatePickerDialog(
            requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                if (direction == "forth") {
                    updateDateDayInViews(
                        LocalDate.of(
                            selectedYear,
                            selectedMonth + 1,
                            selectedDay
                        )
                    )
                }
            }, date.year, date.monthValue, date.dayOfMonth
        )
        datePickerDialog.show()
    }

    private fun updateDateDayInViews(date: LocalDate) {
        val datePattern = DateTimeFormatter.ofPattern("dd MMMM", Locale("ru"))
        val dayOfWeekPattern = DateTimeFormatter.ofPattern("EE", Locale("ru"))
        binding.tvDepartureDate.text = datePattern.format(date)
        binding.tvDepartureDay.text = ", " + dayOfWeekPattern.format(date)
    }


    private fun renderLoading(state: DepartureScreenState.Loading) {
        with(binding) {
            recyclerViewDepartureTickets.isVisible = false
            tvShowAll.isVisible = false
            pbDepartureProgress.isVisible = true
        }
    }

    private fun render(state: DepartureScreenState.Ready) {
        val tickets = state.tickets.takeLast(3)
        val oldList = departureAdapter.items
        with(binding)
        {
            recyclerViewDepartureTickets.isVisible = true
            tvShowAll.isVisible = true
            pbDepartureProgress.isVisible = false
        }

        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int {
                return oldList.size
            }


            override fun getNewListSize(): Int {
                return tickets.size
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition].company != tickets[newItemPosition].company
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition] != tickets[newItemPosition]
            }

        })
        departureAdapter.submitList(tickets)
        diff.dispatchUpdatesTo(departureAdapter)
    }

    companion object {
        fun newInstance(): DepartureFragment = DepartureFragment()
    }
}