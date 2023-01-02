package com.example.fpandc13.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fpandc13.data.network.models.booking.historyUser.Booking
import com.example.fpandc13.databinding.ItemHistoryBinding

class HistoryAdapter (private val onClick: (Booking) -> Unit) :
    ListAdapter<Booking, HistoryAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Booking) {
            binding.apply {
                val date = binding.tvDate
                val status = binding.tvKelas

                val kode = binding.code

                date.text = item?.createdAt
                status.text = "SUCCESS"
                kode.text = "AERO"+item?.id+item?.idTicket+"P"+item?.idPassenger+"LN"+item?.idUsers
                root.setOnClickListener { onClick(item) }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.ViewHolder {
        val binding =
            ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryAdapter.ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount(): Int = currentList.size

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Booking>() {
            override fun areItemsTheSame(oldItem: Booking, newItem: Booking): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Booking, newItem: Booking): Boolean {
                return oldItem == newItem
            }
        }
    }
    }

//
