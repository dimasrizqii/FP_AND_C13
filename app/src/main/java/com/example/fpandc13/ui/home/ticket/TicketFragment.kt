package com.example.fpandc13.ui.home.ticket

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fpandc13.R
import com.example.fpandc13.adapter.SearchAdapter
import com.example.fpandc13.adapter.TicketAdapter
import com.example.fpandc13.adapter.TicketItemClickListener
import com.example.fpandc13.data.network.models.ticket.list.detail.Ticket
import com.example.fpandc13.data.network.service.ticket.AeroplaneTicketApiInterface
import com.example.fpandc13.databinding.FragmentLoginBinding
import com.example.fpandc13.databinding.FragmentTicketBinding
import com.example.fpandc13.ui.home.datapassenger.DataPassengerFragmentArgs
import com.example.fpandc13.ui.home.profile.ProfileViewModel
import com.example.fpandc13.ui.login.LoginViewModel
import com.example.fpandc13.wrapper.Resource
import dagger.hilt.android.AndroidEntryPoint
import java.util.*




@AndroidEntryPoint
class TicketFragment : Fragment() {



    private var _binding: FragmentTicketBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TicketViewModel by viewModels()
    private val userViewModel : ProfileViewModel by viewModels()
    private val authViewModel : LoginViewModel by viewModels()
    private val adapter: TicketAdapter by lazy { TicketAdapter(::onClicked) }


        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            _binding = FragmentTicketBinding.inflate(inflater, container, false)
            return binding.root
    }


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
        authViewModel.getDataStoreToken().observe(viewLifecycleOwner) {
            userViewModel.GetProfileUser("Bearer $it")
        }
        userViewModel.GetProfileUserResponse.observe(viewLifecycleOwner) {
            when(it){
                is Resource.Success ->{
                    Log.d("GetUserProfileResponse", it.data.toString())
                    binding.progressBar.isInvisible
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "Reload Gagal : ObserveGet", Toast.LENGTH_LONG).show()

                }
                is Resource.Empty-> {
                    Toast.makeText(requireContext(), "Field : Empty", Toast.LENGTH_LONG).show()
                }
                is Resource.Loading-> {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_LONG).show()
                }
                else -> {}
            }

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







