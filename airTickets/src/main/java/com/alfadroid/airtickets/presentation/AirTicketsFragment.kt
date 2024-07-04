package com.alfadroid.airtickets.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfadroid.airtickets.R
import com.alfadroid.airtickets.data.retrofit.RetrofitInstance
import com.alfadroid.airtickets.databinding.FragmentAirTicketsBinding
import com.alfadroid.airtickets.domain.Item
import com.alfadroid.airtickets.domain.OffersAdapter
import com.alfadroid.airtickets.domain.RecyclerViewAdapter
import kotlinx.coroutines.launch


class AirTicketsFragment : Fragment() {
    private lateinit var binding: FragmentAirTicketsBinding
    private lateinit var offersAdapter: OffersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAirTicketsBinding.inflate(layoutInflater)

//
//
//        if (isNetworkAvailable()) {
//
//            lifecycleScope.launch {
//
//// https://drive.usercontent.google.com/u/0/uc?id=1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav&export=download
//                val id = "1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav"
//                val export = "download"
//                val response = RetrofitInstance.api.queryAPI(id, export)
//
//                if (response.isSuccessful) {
//                    val offers = response.body()?.offers
////                    offersAdapter.submitList(offers)
//
//                    if (offers?.size == 0) {
//                        binding.recyclerViewOffersFr.visibility = View.GONE
//                        binding.notificationNoNetTv.text =
//                            resources.getString(com.alfadroid.common.R.string.no_offers_this_date)
//                    } else {
//
//                        Log.d("cat", "${offers.toString()}")
//                        binding.recyclerViewOffersFr.visibility = View.VISIBLE
//                        binding.notificationNoNetTv.text = ""
//                    }
//
//                } else {
////                    val emptyData = listOf(ArticleDto("", "", "", SourceDto(""), "", ""))
////                    offersAdapter.submitList(emptyData)
////                    binding.recyclerViewNewsFragment.visibility = View.GONE
//                    binding.notificationNoNetTv.text = resources
//                        .getString(com.alfadroid.common.R.string.site_not_available)
//                }
//
//            }
//
//        } else {
//            binding.recyclerViewOffersFr.visibility = View.GONE
//        }

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

        binding.recyclerViewOffersFr.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = RecyclerViewAdapter(items)
        }

        binding.etDestination.setOnClickListener {
            val bottomSheet = BottomSheetFragment()
            bottomSheet.show(childFragmentManager, "BottomSheetFragment")
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: Network? = connectivityManager.activeNetwork
        val networkCapabilities: NetworkCapabilities? =
            connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities != null &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}