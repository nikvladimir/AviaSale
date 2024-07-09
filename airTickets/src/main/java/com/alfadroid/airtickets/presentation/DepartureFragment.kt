package com.alfadroid.airtickets.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alfadroid.airtickets.databinding.FragmentDepartureBinding

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
            DestinationBottomSheetFragment().show(parentFragmentManager, "BottomSheetFragment")
        }


    }

    override fun onDetach() {
        super.onDetach()
        DestinationBottomSheetFragment().show(parentFragmentManager, "BottomSheetFragment")
    }
}
