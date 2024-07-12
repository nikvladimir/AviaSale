package com.alfadroid.airtickets.presentation.quickbuttons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alfadroid.airtickets.databinding.FragmentWeekendsBinding
import com.alfadroid.destination.presentation.DestinationBottomSheetFragment


class WeekendsFragment : Fragment() {
    private val binding: FragmentWeekendsBinding by viewBinding(FragmentWeekendsBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentWeekendsBinding.inflate(layoutInflater).root
    }

    override fun onDetach() {
        super.onDetach()
        val bottomSheet = DestinationBottomSheetFragment()
        bottomSheet.show(parentFragmentManager, "BottomSheetFragment")
    }

    companion object {
        fun newInstance(): WeekendsFragment = WeekendsFragment()
    }
}
