package com.alfadroid.airtickets.presentation

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import com.alfadroid.airtickets.databinding.FragmentDestinationBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DestinationBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentDestinationBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDestinationBottomSheetBinding.inflate(layoutInflater)

        binding.clIstanbul.setOnClickListener {
            binding.etDestination.text = getString(com.alfadroid.common.R.string.istanbul)
        }
        binding.clSochi.setOnClickListener {
            binding.etDestination.text = getString(com.alfadroid.common.R.string.sochi)
        }
        binding.clPhuket.setOnClickListener {
            binding.etDestination.text = getString(com.alfadroid.common.R.string.phuket)
        }
        binding.ivClearDestination.setOnClickListener { binding.etDestination.text = "" }
//        binding.llComplexRoute.setOnClickListener {
//            parentFragmentManager.beginTransaction().apply {
//                replace()
//            }
//        }

        return binding.root
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
}
