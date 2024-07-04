package com.alfadroid.airtickets.domain

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.alfadroid.airtickets.databinding.RecyclerViewItemBinding

class OffersAdapter()  {

    class ViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {

        private val binding = RecyclerViewItemBinding.bind(itemView)

    }

}
