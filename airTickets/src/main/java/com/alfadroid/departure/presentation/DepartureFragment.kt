package com.alfadroid.departure.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alfadroid.airtickets.databinding.FragmentDepartureBinding
import com.alfadroid.destination.presentation.DestinationBottomSheetFragment

class DepartureFragment : Fragment() {

    private lateinit var binding: FragmentDepartureBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDepartureBinding.inflate(layoutInflater)
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
}
