package com.alfadroid.airtickets.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alfadroid.airtickets.databinding.RecyclerViewOffersItemBinding

data class Item(
    val id: String,
    val imageResId: Int,
    val title: String,
    val town: String,
    val price: String,
)

class AirTicketsAdapter :
    RecyclerView.Adapter<AirTicketsAdapter.ViewHolder>() {

    var items: List<Item> = emptyList()

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

        fun bind(item: Item) {
            with(binding) {
                itemOfferImage.setImageResource(item.imageResId)
                itemText.text = item.title
                itemOfferDestination.text = item.town
                itemOfferPrice.text = item.price
            }
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(list: List<Item>) {
        items = list
    }
}
