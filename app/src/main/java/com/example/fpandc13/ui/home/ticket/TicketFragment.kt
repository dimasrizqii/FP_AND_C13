package com.example.fpandc13.ui.home.ticket

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.fpandc13.R
import com.example.fpandc13.adapter.TicketAdapter
import com.example.fpandc13.adapter.WishlistAdapter
import com.example.fpandc13.data.entity.WishlistEntity
import com.example.fpandc13.data.network.models.ticket.list.detail.Ticket
import com.example.fpandc13.data.network.service.ticket.AeroplaneTicketApiInterface
import com.example.fpandc13.databinding.FragmentTicketBinding
import com.example.fpandc13.ui.home.wishlist.WishlistViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_ticket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class TicketFragment : Fragment() {


    private var _binding: FragmentTicketBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TicketViewModel by viewModels()
    private lateinit var ApiHelper: AeroplaneTicketApiInterface

    private lateinit var wviewModel: WishlistViewModel

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
            _binding = FragmentTicketBinding.inflate(inflater, container, false)
            val view = binding.root
            return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTicket()
        initList()
        observeQueryResult()

        wviewModel = ViewModelProvider(
            this
        ).get(WishlistViewModel::class.java)
        Wishlist()

        if (arguments?.getSerializable("datadetail") != null) {
            val getWishlist = arguments?.getSerializable("datadetail") as
            Glide.with(this)
            binding.bandara.text = getWishlist.airportName
            binding.tvKelas.text = getWishlist.movieName
            binding.DepartureText.text = getWishlist.departure
            binding.ArrivalText.text = getWishlist.arrival
            binding.timeDep.text = getWishlist.departureTime
            binding.timeAriv.text = getWishlist.arrivalTime
            binding.tvPrice.text = getWishlist.price.toString()
        }
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
        viewModel.LiveDataTicket.observe(viewLifecycleOwner) { result ->
            showTicketList(result)
            Log.d(TAG, "Fragment -> $result")
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


    fun Wishlist(){
        if (arguments?.getSerializable("datadetail") != null) {
            val getTicket = arguments?.getSerializable("datadetail") as
            val id = getTicket.id
            val airportName = getTicket.airportName
            val appLogoPath = getTicket.appLogoPath
            val price = getTicket.price
            val departure = getTicket.departure
            val arrival = getTicket.arrival
            val departureTime = getTicket.departureTime
            val arrivalTime = getTicket.arrivalTime
            val kelas = getTicket.kelas

            var _isChecked = false
            CoroutineScope(Dispatchers.IO).launch {
                val count = wviewModel.checkTicket(id)
                withContext(Dispatchers.Main){
                    if (count != null){
                        if (count > 0){
                            binding.btnFavorit.isChecked = true
                            _isChecked = true
                        } else {
                            binding.btnFavorit.isChecked = false
                            _isChecked = false
                        }
                    }
                }
            }

            binding.btnFavorit.setOnClickListener{
                _isChecked = !_isChecked
                if (_isChecked) {
                    wviewModel.addToWishlist(id, airportName, appLogoPath, price, departure, arrival, departureTime, arrivalTime, kelas.toString())
                } else {
                    wviewModel.removeFromWishlist(id, airportName, appLogoPath, price, departure, arrival, departureTime, arrivalTime, kelas.toString())
                }
                binding.btnFavorit.isChecked = _isChecked
            }
        }

    }
}







