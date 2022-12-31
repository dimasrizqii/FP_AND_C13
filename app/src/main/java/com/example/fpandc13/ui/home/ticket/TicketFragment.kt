package com.example.fpandc13.ui.home.ticket

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fpandc13.R
import com.example.fpandc13.adapter.SearchAdapter
import com.example.fpandc13.adapter.TicketAdapter
import com.example.fpandc13.adapter.TicketItemClickListener
import com.example.fpandc13.data.network.models.ticket.list.detail.Ticket
import com.example.fpandc13.data.network.service.ticket.AeroplaneTicketApiInterface
import com.example.fpandc13.databinding.FragmentTicketBinding
import com.example.fpandc13.ui.home.datapassenger.DataPassengerFragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import java.util.*




@AndroidEntryPoint
class TicketFragment : Fragment() {



    private val binding: FragmentTicketBinding by lazy {
        FragmentTicketBinding.inflate(layoutInflater)
    }
    private val viewModel: TicketViewModel by viewModels()
    private val adapter: TicketAdapter by lazy { TicketAdapter(::onClicked) }


        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket, container, false)
    }

//    private val adapterSearch: SearchAdapter by lazy {
//        SearchAdapter (object : TicketItemClickListener {
//            override fun onItemClicked(item: Ticket) {
//                val action = TicketFragmentDirections.actionTicketFragmentToDataPassengerFragment(item)
//                findNavController().navigate(action)
//            }
//
//            override fun onUpdateMenuClicked(ticket: Ticket) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onDeleteMenuClicked(id: Int) {
//                TODO("Not yet implemented")
//            }
//        })
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.getTicket()
        initList()
        observeQueryResult()
        }

    private fun initList() {
        binding.rvTicket.apply {
            adapter = this@TicketFragment.adapter
            layoutManager = LinearLayoutManager(this@TicketFragment.context)
        }
    }

    private fun observeQueryResult() {
        viewModel.state.observe(viewLifecycleOwner) { result ->
            showTicketList(result.ticket)
            Log.d(TAG, "Fragment -> ${result.ticket}")
        }

        viewModel.LiveDataTicket.observe(viewLifecycleOwner) { result ->
            showTicketList(result)
            Log.d(TAG, "Fragment -> ${result}")
        }
    }

    private fun showTicketList(ticket: List<Ticket>) {
        adapter.submitList(ticket)
        binding.rvTicket.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun onClicked(ticket: Ticket) {
        viewModel.LiveDataTicket.observe(viewLifecycleOwner) { result ->
            val direction = TicketFragmentDirections.actionTicketFragmentToDataPassengerFragment(ticket)
            findNavController().navigate(direction)
            Log.d(TAG, "userId -> $result.user?.id")
        }
    }
    }







