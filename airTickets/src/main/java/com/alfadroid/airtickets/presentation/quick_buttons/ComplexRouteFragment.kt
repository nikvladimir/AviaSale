package com.alfadroid.airtickets.presentation.quick_buttons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alfadroid.airtickets.databinding.FragmentComplexRouteBinding
import com.alfadroid.airtickets.presentation.DestinationBottomSheetFragment

class ComplexRouteFragment : Fragment() {
    private lateinit var binding: FragmentComplexRouteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentComplexRouteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        val bottomSheet = DestinationBottomSheetFragment()
        bottomSheet.show(parentFragmentManager, "BottomSheetFragment")
    }

}