package com.alfadroid.viewAllTickets.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alfadroid.airtickets.databinding.FragmentViewAllTicketsBinding


class ViewAllTicketsFragment : Fragment() {
    private val binding: FragmentViewAllTicketsBinding by viewBinding(FragmentViewAllTicketsBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentViewAllTicketsBinding.inflate(layoutInflater).root
    }

    companion object {
        fun newInstance(): ViewAllTicketsFragment = ViewAllTicketsFragment()
    }
}