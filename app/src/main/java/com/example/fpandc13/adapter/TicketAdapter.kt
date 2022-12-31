package com.example.fpandc13.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fpandc13.R
import com.example.fpandc13.data.network.models.ticket.list.detail.Ticket
import com.example.fpandc13.databinding.ItemTicketListBinding

class TicketAdapter (private val onClick: (Ticket) -> Unit) :
    ListAdapter<Ticket, TicketAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: ItemTicketListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Ticket) {
            binding.apply {
                binding.price.text = item?.price.toString()
                binding.bandara.text = item?.airport?.airportName
                binding.timeAriv.text = item?.arrivalDate
                binding.timeDep.text = item?.departureDate
                binding.dateDepEdit.text = item?.airport?.airportLocation
                binding.tvKelas.text = item?.classX

                root.setOnClickListener { onClick(item) }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketAdapter.ViewHolder {
        val binding =
            ItemTicketListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TicketAdapter.ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount(): Int = currentList.size

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Ticket>() {
            override fun areItemsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
                return oldItem == newItem
            }
        }
    }
}



