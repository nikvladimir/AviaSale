package com.alfadroid.airtickets.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.alfadroid.airtickets.databinding.RecyclerViewOffersItemBinding

class OffersAdapter()  {

    class ViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {

        private val binding = RecyclerViewOffersItemBinding.bind(itemView)

    }

}
