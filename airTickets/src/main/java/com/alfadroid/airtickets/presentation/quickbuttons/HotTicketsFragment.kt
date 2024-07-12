package com.alfadroid.airtickets.presentation.quickbuttons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alfadroid.airtickets.databinding.FragmentHotTicketsBinding
import com.alfadroid.airtickets.presentation.HostAirTicketsFragment
import com.alfadroid.destination.presentation.DestinationBottomSheetFragment

class HotTicketsFragment : Fragment() {
    private val binding: FragmentHotTicketsBinding by viewBinding(FragmentHotTicketsBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentHotTicketsBinding.inflate(layoutInflater).root
    }

    override fun onDetach() {
        super.onDetach()
        val bottomSheet = DestinationBottomSheetFragment()
        bottomSheet.show(parentFragmentManager, "BottomSheetFragment")
    }

    companion object {
        fun newInstance(): HostAirTicketsFragment = HostAirTicketsFragment()
    }
}
