package com.alfadroid.airtickets.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfadroid.airtickets.R
import com.alfadroid.airtickets.databinding.FragmentAirTicketsBinding
import com.alfadroid.airtickets.domain.Item
import com.alfadroid.airtickets.domain.RecyclerViewAdapter


class AirTicketsFragment : Fragment() {
    private lateinit var binding: FragmentAirTicketsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAirTicketsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = listOf(
            Item(R.drawable.ic_launcher_foreground, "Item 1"),
            Item(R.drawable.ic_launcher_foreground, "Item 2"),
            Item(R.drawable.ic_launcher_foreground, "Item 3"),
            Item(R.drawable.ic_launcher_foreground, "Item 4"),
            Item(R.drawable.ic_launcher_foreground, "Item 5")
        )

        binding.recyclerViewRecommendationFr.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = RecyclerViewAdapter(items)
        }

        binding.etDestination.setOnClickListener {
            val bottomSheet = BottomSheetFragment()
            bottomSheet.show(childFragmentManager, "BottomSheetFragment")
        }
    }
}