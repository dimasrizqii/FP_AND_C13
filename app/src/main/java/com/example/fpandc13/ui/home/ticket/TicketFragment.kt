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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fpandc13.R
import com.example.fpandc13.adapter.TicketAdapter
import com.example.fpandc13.data.network.models.ticket.list.detail.Ticket
import com.example.fpandc13.data.network.service.ticket.AeroplaneTicketApiInterface
import com.example.fpandc13.databinding.FragmentTicketBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_ticket.*
import kotlinx.android.synthetic.main.fragment_ticket.view.*
import java.util.*




@AndroidEntryPoint
class TicketFragment : Fragment() {

    private val binding: FragmentTicketBinding by lazy {
        FragmentTicketBinding.inflate(layoutInflater)
    }
    private val viewModel: TicketViewModel by viewModels()
    private lateinit var ApiHelper: AeroplaneTicketApiInterface

    private val adapter: TicketAdapter by lazy { TicketAdapter(::onClicked) }

    private val adapter2: TicketAdapter by lazy {
        TicketAdapter {

        }
    }

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val llm = LinearLayoutManager(this@TicketFragment.context)
        llm.orientation = LinearLayoutManager.VERTICAL
        binding.rvTicket.setLayoutManager(llm)
        binding.rvTicket.setAdapter(adapter)

        viewModel.getTicket()
        initList()
        observeQueryResult()
        }

    private fun initList() {
        rvTicket.apply {
            adapter = this@TicketFragment.adapter
            layoutManager = LinearLayoutManager(this@TicketFragment.context)
        }
    }

    private fun observeQueryResult() {
        viewModel.state.observe(viewLifecycleOwner) { result ->
            showTicketList(result.ticket)
            Log.d(TAG, "Fragment -> ${result.ticket}")
        }
    }

    private fun showTicketList(ticket: List<Ticket>) {
        adapter.submitList(ticket)
        binding.rvTicket.adapter = adapter
        binding.rvTicket.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun onClicked(ticket: Ticket) {
        viewModel.state.observe(viewLifecycleOwner) { result ->
            Log.d(TAG, "userId -> $result.user?.id")
        }
    }
    }







