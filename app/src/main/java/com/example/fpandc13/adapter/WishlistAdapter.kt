package com.example.fpandc13.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.fpandc13.R
import com.example.fpandc13.data.entity.WishlistEntity
import com.example.fpandc13.data.utils.Utils
import com.example.fpandc13.databinding.ItemWishlistBinding

class WishlistAdapter(private val listData: List<WishlistEntity>) :
    RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder>() {

    class WishlistViewHolder(private val binding: ItemWishlistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: WishlistEntity) {
            with(binding) {
                DepartureText.text = data.departure
                ArrivalText.text = data.arrival
                tvPrice.text = Utils.formatRupiah(data.price!!)
                tvKelas.text = data.kelas
                bandara.text = data.airportName
                timeDep.text = data.departureTime
                timeAriv.text = data.arrivalTime
            }
            binding.itemWishlist.setOnClickListener {
                it.findNavController()
                    .navigate(R.id.action_wishlistFragment_to_firstFragment)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder =
        WishlistViewHolder(
            ItemWishlistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: WishlistViewHolder, position: Int) {
        holder.bind(listData[position])
    }
}