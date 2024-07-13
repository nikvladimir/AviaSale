package com.alfadroid.airtickets.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alfadroid.airtickets.R
import com.alfadroid.airtickets.databinding.FragmentAirTicketsBinding
import com.alfadroid.airtickets.domain.CyrillicInputFilter
import com.alfadroid.departure.presentation.DepartureFragment
import com.alfadroid.destination.presentation.DestinationBottomSheetFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AirTicketsFragment : Fragment() {
    private val binding: FragmentAirTicketsBinding by viewBinding(FragmentAirTicketsBinding::bind)
    private val viewModel by viewModel<AirTicketsViewModel>()
    private val airTicketsAdapter = AirTicketsAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentAirTicketsBinding.inflate(layoutInflater).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewOffersFr.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = airTicketsAdapter
        }

        binding.tvDeparture.apply {
            filters = arrayOf(CyrillicInputFilter())
            setOnClickListener {
                parentFragmentManager.commit {
                    replace(R.id.hostAirTicketsFragment, DepartureFragment.newInstance())
                    addToBackStack(null)
                }
            }
        }

        binding.tvDestination.setOnClickListener {
            val bottomSheet = DestinationBottomSheetFragment()
            bottomSheet.show(parentFragmentManager, "BottomSheetFragment")
        }


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.screenState.collect { state ->
                    when (state) {
                        AirTicketsScreenState.Loading -> {} // TODO
                        is AirTicketsScreenState.Error -> {} // TODO
                        is AirTicketsScreenState.Ready -> {
                            render(state)
                        }
                    }
                }
            }
        }
    }

    private fun render(state: AirTicketsScreenState.Ready) {
        val offers = state.offers
        val oldList = airTicketsAdapter.items
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int {
                return oldList.size
            }

            override fun getNewListSize(): Int {
                return offers.size
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition].id == offers[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition] == offers[newItemPosition]
            }

        })
        airTicketsAdapter.submitList(offers)
        diff.dispatchUpdatesTo(airTicketsAdapter)
    }

    companion object {
        fun newInstance(): AirTicketsFragment = AirTicketsFragment()
    }
}
