package com.alfadroid.departure.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alfadroid.airtickets.databinding.RecyclerViewOffersItemBinding
import com.alfadroid.departure.domain.models.TicketItem

class DepartureAdapter :
    RecyclerView.Adapter<DepartureAdapter.ViewHolder>() {

    var items: List<TicketItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            binding = RecyclerViewOffersItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )

    class ViewHolder(
        private val binding: RecyclerViewOffersItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TicketItem) {
            with(binding) {
//                itemOfferImage.setImageResource(item.imageResId)
//                itemText.text = item.title
//                itemOfferDestination.text = item.town
//                itemOfferPrice.text = item.price
            }
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(list: List<TicketItem>) {
        items = list
    }
}
