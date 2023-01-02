package com.example.fpandc13.ui.home.history

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fpandc13.adapter.HistoryAdapter
import com.example.fpandc13.data.network.models.booking.historyUser.Booking
import com.example.fpandc13.data.network.service.booking.AeroplaneBookingApiInterface
import com.example.fpandc13.databinding.FragmentHistoryBinding
import com.example.fpandc13.ui.activity.Home.HomeActivity
import com.example.fpandc13.ui.home.profile.ProfileViewModel
import com.example.fpandc13.ui.login.LoginViewModel
import com.example.fpandc13.wrapper.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HistoryViewModel by viewModels()
    private val userViewModel : ProfileViewModel by viewModels()
    private val authViewModel : LoginViewModel by viewModels()
    private lateinit var ApiHelper : AeroplaneBookingApiInterface

    private val adapter: HistoryAdapter by lazy { HistoryAdapter(::onClicked) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GetProfile()
        initList()
        observeQueryResult()
        binding.btnBack.setOnClickListener(){
            activity?.let { it ->
                val intent = Intent(it, HomeActivity::class.java)
                it.startActivity(intent)}
        }

    }

    private fun initList() {
        binding.rvHistory.apply {
            adapter = this@HistoryFragment.adapter
            layoutManager = LinearLayoutManager(this@HistoryFragment.context)
        }
    }

    private fun GetProfile() {

        authViewModel.getDataStoreToken().observe(viewLifecycleOwner) {
            viewModel.GetHistoryUser("Bearer $it")
        }
        viewModel.LiveDataBooking.observe(viewLifecycleOwner) { result ->
            showTicketList(result)
            Log.d(ContentValues.TAG, "Fragment -> ${result}")
        }
        viewModel.state.observe(viewLifecycleOwner) { result ->
            showTicketList(result.booking)
            Log.d(ContentValues.TAG, "Fragment -> ${result.booking}")
        }

    }



    private fun observeQueryResult(){
        viewModel.HistoryResponse.observe(viewLifecycleOwner) { result ->
            showTicketList(result)
        }
        userViewModel.GetProfileUserResponse.observe(viewLifecycleOwner) {
            when(it){
                is Resource.Success ->{
                    Log.d("GetUserProfileResponse", it.data.toString())
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "Reload Gagal : ObserveGet", Toast.LENGTH_LONG).show()

                }
                is Resource.Empty-> {
                    Toast.makeText(requireContext(), "Field : Empty", Toast.LENGTH_LONG).show()
                }
                is Resource.Loading-> {
                    binding.progressBar.isVisible
                }
                else -> {}
            }

        }
    }


    private fun showTicketList(booking: List<Booking>) {
        adapter.submitList(booking)
        binding.rvHistory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }



    private fun onClicked(item : Booking) {
        viewModel.BookingHistory.observe(viewLifecycleOwner) { result ->
            val direction = HistoryFragmentDirections.actionHistoryFragmentToHiltTicketDetailFragment2()
            findNavController().navigate(direction)
            Log.d(ContentValues.TAG, "userId -> $result.user?.id")
        }
    }


}
