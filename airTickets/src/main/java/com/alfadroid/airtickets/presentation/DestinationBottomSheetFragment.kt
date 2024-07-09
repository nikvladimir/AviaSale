package com.alfadroid.airtickets.presentation

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.alfadroid.airtickets.R
import com.alfadroid.airtickets.databinding.FragmentDestinationBottomSheetBinding
import com.alfadroid.airtickets.presentation.quick_buttons.ComplexRouteFragment
import com.alfadroid.airtickets.presentation.quick_buttons.HotTicketsFragment
import com.alfadroid.airtickets.presentation.quick_buttons.WeekendsFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DestinationBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentDestinationBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDestinationBottomSheetBinding.inflate(layoutInflater)
        return binding.root
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

        binding.llComplexRoute.setOnClickListener(fragmentTransaction(ComplexRouteFragment()))
        binding.llWeekends.setOnClickListener(fragmentTransaction(WeekendsFragment()))
        binding.llHotTickets.setOnClickListener(fragmentTransaction(HotTicketsFragment()))
        binding.tvDeparture.setOnClickListener(fragmentTransaction(DepartureFragment()))

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
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.hostAirTicketsFragment, fragment)
                .addToBackStack(null)
                .commit()
            dismiss()
        }
    }
}
