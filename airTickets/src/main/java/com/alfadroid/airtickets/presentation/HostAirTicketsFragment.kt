package com.alfadroid.airtickets.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alfadroid.airtickets.databinding.FragmentHostAirTicketsBinding


class HostAirTicketsFragment : Fragment() {
    private lateinit var binding: FragmentHostAirTicketsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHostAirTicketsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.beginTransaction().apply {
            replace(binding.hostAirTicketsFragment.id, AirTicketsFragment())
                .commit()
        }
    }
}