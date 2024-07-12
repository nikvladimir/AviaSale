package com.alfadroid.hotels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alfadroid.hotels.databinding.FragmentHotelFragmentBinding

class HotelsFragment : Fragment() {
    private val binding: FragmentHotelFragmentBinding by viewBinding(FragmentHotelFragmentBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentHotelFragmentBinding.inflate(layoutInflater).root
    }

    companion object {
        fun newInstance(): HotelsFragment = HotelsFragment()
    }
}
