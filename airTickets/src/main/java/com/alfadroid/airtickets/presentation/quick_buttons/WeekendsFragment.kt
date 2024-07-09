package com.alfadroid.airtickets.presentation.quick_buttons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alfadroid.airtickets.R
import com.alfadroid.airtickets.presentation.DestinationBottomSheetFragment


class WeekendsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weekends, container, false)
    }

    override fun onDetach() {
        super.onDetach()
        val bottomSheet = DestinationBottomSheetFragment()
        bottomSheet.show(parentFragmentManager, "BottomSheetFragment")
    }
}