package com.alfadroid.airtickets.presentation.quick_buttons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alfadroid.airtickets.databinding.FragmentComplexRouteBinding

class ComplexRouteFragment : Fragment() {
    private lateinit var binding: FragmentComplexRouteBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentComplexRouteBinding.inflate(layoutInflater)
        return binding.root
    }

}