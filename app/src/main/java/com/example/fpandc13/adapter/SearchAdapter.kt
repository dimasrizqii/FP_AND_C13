package com.example.fpandc13.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fpandc13.data.network.models.ticket.list.detail.Ticket
import com.example.fpandc13.databinding.ItemPassangerTicketBinding
import com.example.fpandc13.databinding.ItemTicketListBinding

class SearchAdapter(
    private val listener: TicketItemClickListener
) :
    RecyclerView.Adapter<SearchAdapter.SearchResultViewHolder>() {
    private val diffCallback = object : DiffUtil.ItemCallback<Ticket>() {
        override fun areItemsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    private var isAdmin = false

    fun submitList(list: List<Ticket>?) {
        differ.submitList(list)
    }

    fun checkIfUserIsAdmin(isAdmin: Boolean) {
        this.isAdmin =  isAdmin
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val binding = ItemTicketListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchResultViewHolder(binding, listener, isAdmin)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class SearchResultViewHolder(
        private val binding: ItemTicketListBinding,
        private val listener: TicketItemClickListener,
        private val isAdmin: Boolean
    ) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: Ticket) {
            with(binding) {
                with(item) {
                    binding.price.text = item?.price.toString()
                    binding.bandara.text = item?.airport?.airportName
                    binding.timeAriv.text = item?.arrivalDate
                    binding.timeDep.text = item?.departureDate
                    binding.dateDepEdit.text = item?.airport?.airportLocation
                    binding.tvKelas.text = item?.classX

                    itemView.setOnClickListener {
                        listener.onItemClicked(this)
                    }
                }
            }
        }
    }
}

interface TicketItemClickListener {
    fun onItemClicked(ticket: Ticket)
    fun onUpdateMenuClicked(ticket: Ticket)
    fun onDeleteMenuClicked(id: Int)
}