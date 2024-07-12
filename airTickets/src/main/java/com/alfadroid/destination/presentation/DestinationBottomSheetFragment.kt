package com.alfadroid.destination.presentation

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alfadroid.airtickets.R
import com.alfadroid.airtickets.databinding.FragmentDestinationBottomSheetBinding
import com.alfadroid.airtickets.presentation.quickbuttons.ComplexRouteFragment
import com.alfadroid.airtickets.presentation.quickbuttons.HotTicketsFragment
import com.alfadroid.airtickets.presentation.quickbuttons.WeekendsFragment
import com.alfadroid.departure.presentation.DepartureFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DestinationBottomSheetFragment : BottomSheetDialogFragment() {

    private val binding: FragmentDestinationBottomSheetBinding by viewBinding(
        FragmentDestinationBottomSheetBinding::bind
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return FragmentDestinationBottomSheetBinding.inflate(layoutInflater).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.clIstanbul.setOnClickListener {
            binding.tvDestination.text = getString(com.alfadroid.common.R.string.istanbul)
        }
        binding.clSochi.setOnClickListener {
            binding.tvDestination.text = getString(com.alfadroid.common.R.string.sochi)
        }
        binding.clPhuket.setOnClickListener {
            binding.tvDestination.text = getString(com.alfadroid.common.R.string.phuket)
        }

        binding.ivClearDestination.setOnClickListener { binding.tvDestination.text = "" }
        binding.llAnywhere.setOnClickListener {
            binding.tvDestination.text = getString(com.alfadroid.common.R.string.any_city)
        }

        binding.llComplexRoute.setOnClickListener(fragmentTransaction(ComplexRouteFragment.newInstance()))
        binding.llWeekends.setOnClickListener(fragmentTransaction(WeekendsFragment.newInstance()))
        binding.llHotTickets.setOnClickListener(fragmentTransaction(HotTicketsFragment.newInstance()))
        binding.tvDeparture.setOnClickListener(fragmentTransaction(DepartureFragment.newInstance()))

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            val bottomSheet = bottomSheetDialog.findViewById<FrameLayout>(
                com.google.android.material.R.id.design_bottom_sheet
            )

            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)
                setupFullHeight(it)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        return dialog
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

    private fun fragmentTransaction(fragment: Fragment): View.OnClickListener {
        return View.OnClickListener {
            childFragmentManager.commit {
                replace(R.id.hostAirTicketsFragment, fragment).addToBackStack(null)
            }
            dismiss()
        }
    }
}
