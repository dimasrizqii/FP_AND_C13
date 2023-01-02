package com.example.fpandc13.ui.home.search

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fpandc13.R
import com.example.fpandc13.adapter.SearchAdapter
import com.example.fpandc13.adapter.TicketAdapter
import com.example.fpandc13.adapter.TicketItemClickListener
import com.example.fpandc13.data.network.models.ticket.list.detail.Ticket
import com.example.fpandc13.databinding.FragmentDashboardBinding
import com.example.fpandc13.databinding.FragmentSearchResultBinding
import com.example.fpandc13.ui.home.profile.ProfileViewModel
import com.example.fpandc13.ui.home.ticket.TicketFragmentDirections
import com.example.fpandc13.ui.home.ticket.TicketViewModel
import com.example.fpandc13.ui.login.LoginViewModel
import com.example.fpandc13.wrapper.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultFragment : Fragment()  {

    private var _binding: FragmentSearchResultBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()
    private val userViewModel : ProfileViewModel by viewModels()
    private val authViewModel : LoginViewModel by viewModels()

    private val args: Hilt_SearchResultFragmentArgs by navArgs()

    private lateinit var accessToken: String

    private val adapter: TicketAdapter by lazy { TicketAdapter(::onClicked) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authViewModel.getDataStoreToken().observe(viewLifecycleOwner) {
            getTickets()
            initList()
            observeQueryResult()
        }

    }

    private fun getTickets() {
        authViewModel.getDataStoreToken().observe(viewLifecycleOwner) {
            accessToken = "$it"
        args.dataSearchTicket.let {
            viewModel.getTickets(
                "Bearer "+ accessToken,
                it.airportName,
                it.airportLocation,
                it.departureDate,
                it.arrivalDate,
                it.price,
                it.classX
            )
        }}
        Log.d("args", args.dataSearchTicket.toString())
    }

    private fun observeQueryResult() {
        viewModel.state.observe(viewLifecycleOwner) { result ->
            showTicketList(result.ticket)
            Log.d(ContentValues.TAG, "Fragment -> ${result.ticket}")
        }
        viewModel.LiveDataTicket.observe(viewLifecycleOwner) { result ->
            showTicketList(result)
            Log.d(ContentValues.TAG, "Fragment -> ${result}")
        }
    }

    private fun showTicketList(ticket: List<Ticket>) {
        adapter.submitList(ticket)
        binding.rvResultTicket.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun initList() {
        binding.rvResultTicket.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@SearchResultFragment.adapter
        }
    }

    private fun onClicked(ticket: Ticket) {
        viewModel.LiveDataTicket.observe(viewLifecycleOwner) { result ->
            val direction = Hilt_SearchResultFragmentDirections.actionHiltSearchResultFragmentToDataPassengerFragment(ticket)
            findNavController().navigate(direction)
            Log.d(ContentValues.TAG, "userId -> $result.user?.id")
        }
    }


}