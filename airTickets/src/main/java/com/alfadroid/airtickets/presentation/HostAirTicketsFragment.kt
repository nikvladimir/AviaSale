package com.alfadroid.airtickets.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alfadroid.airtickets.R
import com.alfadroid.airtickets.databinding.FragmentHostAirTicketsBinding


class HostAirTicketsFragment : Fragment() {
    private val binding: FragmentHostAirTicketsBinding by viewBinding(FragmentHostAirTicketsBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentHostAirTicketsBinding.inflate(layoutInflater).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            parentFragmentManager.commit {
                replace(R.id.hostAirTicketsFragment, AirTicketsFragment.newInstance())
            }
        }
    }

    companion object {
        fun newInstance(): HostAirTicketsFragment = HostAirTicketsFragment()
    }
}