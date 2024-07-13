package com.alfadroid.airtickets.presentation.quickbuttons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alfadroid.airtickets.databinding.FragmentComplexRouteBinding

class ComplexRouteFragment : Fragment() {
    private val binding: FragmentComplexRouteBinding by viewBinding(FragmentComplexRouteBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentComplexRouteBinding.inflate(layoutInflater).root
    }

    companion object {
        fun newInstance(): ComplexRouteFragment = ComplexRouteFragment()
    }
}
