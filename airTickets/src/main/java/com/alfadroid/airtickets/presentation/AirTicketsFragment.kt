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
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alfadroid.airtickets.R
import com.alfadroid.airtickets.databinding.FragmentAirTicketsBinding
import com.alfadroid.airtickets.domain.CyrillicInputFilter
import com.alfadroid.airtickets.presentation.quickbuttons.ComplexRouteFragment
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

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                        viewModel.screenState.collect { state ->

                            when (state) {
                                AirTicketsScreenState.Loading -> {}
                                is AirTicketsScreenState.Error -> {}
                                is AirTicketsScreenState.Ready -> {
                                    render(state)
                                }
                            }
                        }
                    }
                }

                binding.tvMusicHeader.setOnClickListener {
                    childFragmentManager.commit {
                        replace(R.id.hostAirTicketsFragment, ComplexRouteFragment.newInstance())
                            .addToBackStack(null)
                    }
                }

                binding.recyclerViewOffersFr.apply {
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = airTicketsAdapter
                    airTicketsAdapter.submitList(emptyList())
                }

                binding.tvDestination.setOnClickListener {
                    val bottomSheet = DestinationBottomSheetFragment()
                    bottomSheet.show(childFragmentManager, "BottomSheetFragment")
                }

                binding.tvDeparture.filters = arrayOf(CyrillicInputFilter())
            }
        }
    }

    private fun render(state: AirTicketsScreenState.Ready) {
        with(binding) {
            airTicketsAdapter.submitList(state.offers)
            airTicketsAdapter.notifyDataSetChanged()
        }
    }

    companion object {
        fun newInstance(): AirTicketsFragment = AirTicketsFragment()
    }
}
