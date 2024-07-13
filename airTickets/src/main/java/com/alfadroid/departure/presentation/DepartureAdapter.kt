package com.alfadroid.departure.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alfadroid.airtickets.databinding.RecyclerViewDepartureTicketItemBinding
import com.alfadroid.departure.domain.models.TicketItem

class DepartureAdapter :
    RecyclerView.Adapter<DepartureAdapter.ViewHolder>() {

    var items: List<TicketItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            binding = RecyclerViewDepartureTicketItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )

    class ViewHolder(
        private val binding: RecyclerViewDepartureTicketItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TicketItem) {
            with(binding) {
                tvDepartureTitle.text = item.company
                ivDepartureCompanyLogo.setImageResource(item.companyImageRes)
                tvDeparturePrice.text = item.price
                tvDepartureTimeRange.text = item.timeRanges
            }
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])


    override fun getItemCount(): Int =
        items.size


    fun submitList(list: List<TicketItem>) {
        items = list
    }
}