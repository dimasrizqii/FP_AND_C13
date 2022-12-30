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
                binding.dateDepEdit.text = item?.departureDate
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

//class TicketAdapter(private val itemClick: (Ticket) -> Unit) : RecyclerView.Adapter<postViewHolder>(){
//
//
//    private var items: MutableList<Ticket> = mutableListOf()
//
//    fun setItems(items: List<Ticket>) {
//        this.items.clear()
//        this.items.addAll(items)
//        notifyDataSetChanged()
//    }
//    fun clearItems() {
//        this.items.clear()
//        notifyDataSetChanged()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): postViewHolder {
//        val binding = ItemTicketListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return postViewHolder(binding, itemClick)
//    }
//
//
//    override fun onBindViewHolder(holder: postViewHolder, position: Int) {
//        holder.bindView(items[position])
//    }
//
//
//    override fun getItemCount(): Int = items.size
//
//}
//
//
//
//class postViewHolder (private val binding: ItemTicketListBinding, val itemClick: (Ticket) -> Unit)
//    : RecyclerView.ViewHolder(binding.root){
//
//        fun bindView(item: Ticket) {
//            with(item) { itemView.setOnClickListener { itemClick(this) }
//            binding.price.text = item?.price.toString()
//            binding.bandara.text = item?.airport?.airportName
//            binding.timeAriv.text = item?.arrivalDate
//            binding.timeDep.text = item?.departureDate
//            binding.dateDepEdit.text = item?.departureDate
//            binding.tvKelas.text = item?.classX
//            }
//}}

//class TicketAdapter () :
//    ListAdapter<Ticket, TicketAdapter.ViewHolder>(DIFF_CALLBACK) {
//
//    inner class ViewHolder(private val binding: ItemTicketListBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(ticket: Ticket) {
//            binding.apply {
//            binding.price.text = ticket?.price.toString()
//            binding.bandara.text = ticket?.airport?.airportName
//            binding.timeAriv.text = ticket?.arrivalDate
//            binding.timeDep.text = ticket?.departureDate
//            binding.dateDepEdit.text = ticket?.departureDate
//            binding.tvKelas.text = ticket?.classX
//            }
//        }
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketAdapter.ViewHolder {
//        val binding =
//            ItemTicketListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: TicketAdapter.ViewHolder, position: Int) {
//        holder.bind(currentList[position])
//    }
//
//    override fun getItemCount(): Int = currentList.size
//
//    companion object {
//
//        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Ticket>() {
//            override fun areItemsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
//                return oldItem.id == newItem.id
//            }
//
//            override fun areContentsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
//                return oldItem == newItem
//            }
//        }
//    }
//}
//    private var ticket: MutableList<Ticket>,
//    private val onTicketClick: (ticket: Ticket) -> Unit
//) : RecyclerView.Adapter<TicketAdapter.TicketViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
//        val view = LayoutInflater
//            .from(parent.context)
//            .inflate(R.layout.item_ticket_list, parent, false)
//        return TicketViewHolder(view)
//    }
//
//
//    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
//        holder.bind(ticket[position])
//    }
//
//
//    inner class TicketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//

//
//
//            itemView.setOnClickListener { onTicketClick.invoke(ticket) }
//        }
//    }
//
//    override fun getItemCount(): Int {
//        TODO("Not yet implemented")
//    }
//}
// context: Context, ticket: ArrayList<Ticket>) :
//    ArrayAdapter<Ticket>(context, 0, ticket) {
//
//    private val filter = TicketFilter(ticket)
//
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val ticket = getItem(position)
//        val view = LayoutInflater.from(context).inflate(R.layout.item_ticket_list, parent, false)
//        val binding = ItemTicketListBinding.bind(view)
//        binding.price.text = ticket?.price.toString()
//        binding.bandara.text = ticket?.airport?.airportName
//        binding.timeAriv.text = ticket?.arrivalDate
//        binding.timeDep.text = ticket?.departureDate
//        binding.dateDepEdit.text = ticket?.departureDate
//        binding.tvKelas.text = ticket?.classX
//
//        return view
//    }
//
//    override fun getFilter() = filter
//
//    inner class TicketFilter(private val originalList: List<Ticket>) : Filter() {
//
//        private val ticket: ArrayList<Ticket> = ArrayList()
//
//        init {
//            synchronized (this) {
//                ticket.addAll(originalList)
//            }
//        }
//
//        override fun performFiltering(p0: CharSequence?): FilterResults {
//            if (p0 == null) return FilterResults()
//
//            val result = FilterResults()
//
//            if (p0.isNotEmpty()) {
//
//                ticket.filterTo(ticket) { isWithinConstraint(it, p0) }
//
//                result.count = ticket.size
//                result.values = ticket
//
//            } else {
//                synchronized(this) {
//                    result.values = ticket
//                    result.count = ticket.size
//                }
//
//            }
//            return result
//        }
//
//        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
//            if (p1?.values == null) return
//
//            @Suppress("UNCHECKED_CAST")
//            val filtered = p1.values as ArrayList<Ticket>
//
//            if (p1.count > 0) {
//                clear()
//                addAll(filtered)
//                notifyDataSetChanged()
//            } else {
//                notifyDataSetInvalidated()
//            }
//        }
//
//        override fun convertResultToString(resultValue: Any?): CharSequence {
//            return (resultValue as Ticket).classX.toString()
//        }
//
//        private fun isWithinConstraint(ticket: Ticket, constraint: CharSequence): Boolean {
//            return ticket.classX?.toLowerCase()?.contains(constraint, true)!!
//        }
//    }


