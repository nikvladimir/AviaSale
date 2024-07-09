package com.alfadroid.airtickets.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfadroid.airtickets.R
import com.alfadroid.airtickets.databinding.FragmentAirTicketsBinding
import com.alfadroid.airtickets.domain.CyrillicInputFilter
import com.alfadroid.airtickets.presentation.quick_buttons.ComplexRouteFragment

class AirTicketsFragment : Fragment() {
    private lateinit var binding: FragmentAirTicketsBinding
    private lateinit var offersAdapter: OffersAdapter
    val viewModel: AirTicketsViewModel by viewModels()
    val airTicketsAdapter = RecyclerViewAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAirTicketsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.screenState.collect { state ->
//                    when (state) {
//                        AirTicketsScreenState.Loading -> {}
//                        is AirTicketsScreenState.Error -> {}
//                        is AirTicketsScreenState.Ready -> {
//                            render(state)
//                        }
//                    }
//                }
//            }
//        }

        val items = listOf(
            Item(R.drawable.icon_launcher_foreground, "Item 1"),
            Item(R.drawable.icon_launcher_foreground, "Item 2"),
            Item(R.drawable.icon_launcher_foreground, "Item 3"),
            Item(R.drawable.icon_launcher_foreground, "Item 4"),
            Item(R.drawable.icon_launcher_foreground, "Item 5")
        )

        binding.tvMusicHeader.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.hostAirTicketsFragment, ComplexRouteFragment())
            }.addToBackStack(null).commit()
        }

        binding.recyclerViewOffersFr.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = airTicketsAdapter
            airTicketsAdapter.submitList(items)
        }

        binding.etDestination.setOnClickListener {
            val bottomSheet = DestinationBottomSheetFragment()
            bottomSheet.show(parentFragmentManager, "BottomSheetFragment")
        }

        binding.etDeparture.filters = arrayOf(CyrillicInputFilter())
    }

    private fun render(state: AirTicketsScreenState.Ready) {

        val items = listOf(
            Item(R.drawable.icon_launcher_foreground, "Igvervgerbgrebv"),
            Item(R.drawable.icon_launcher_foreground, "Item 2"),
            Item(R.drawable.icon_launcher_foreground, "Item 3"),
            Item(R.drawable.icon_launcher_foreground, "Item 4"),
            Item(R.drawable.icon_launcher_foreground, "Item 5")
        )
        with(binding) {
//            etDeparture.text = state.departure
//            etDestination.text = state.destination
            airTicketsAdapter.submitList(items)
        }
    }
}