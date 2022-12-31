package com.example.fpandc13.ui.home.datapassenger

import android.content.ContentValues
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.fpandc13.R
import com.example.fpandc13.data.network.models.auth.login.LoginRequestBody
import com.example.fpandc13.data.network.models.passenger.create.CreatePassengerRequestBody
import com.example.fpandc13.databinding.ActivityHomeBinding
import com.example.fpandc13.databinding.FragmentDataPassengerBinding
import com.example.fpandc13.databinding.FragmentTicketBinding
import com.example.fpandc13.wrapper.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DataPassengerFragment : Fragment() {


    private var _bindingFrag: FragmentDataPassengerBinding? = null
    private val binding get() = _bindingFrag!!

    private val args: DataPassengerFragmentArgs by navArgs()
    private val viewModel: DataPassengerViewModel by viewModels()


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
        _bindingFrag = FragmentDataPassengerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        observeQueryResult()
        initView()
        binding.btnCheckout.setOnClickListener { CreatePassenger() }

    }


    private fun CreatePassenger() {
        if (validateInput()) {
            val nama = binding.edtName.text.toString().trim()
            val nik = binding.inputNik.text.toString().trim()

            binding.edtName.isEnabled = false
            binding.inputNik.isEnabled = false
            viewModel.postPassengers(parseFormIntoEntity(nama, nik))}
        findNavController().navigate(R.id.action_dataPassengerFragment_to_bookingRoundFragment)
        Toast.makeText(requireContext(), "Data passenger berhasil ditambahkan", Toast.LENGTH_LONG).show()
    }


    private fun observeQueryResult() {
        viewModel.LiveDataTicket.observe(viewLifecycleOwner) { result ->
            Log.d(ContentValues.TAG, "Fragment -> ${result}")
        }
        viewModel.CreatePassenger.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success ->{
                    Log.d("VerifyResponse", it.data.toString())
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "Error : Kesalahan ", Toast.LENGTH_LONG).show()
                }
                is Resource.Empty -> {
                    Toast.makeText(requireContext(), "Empty : Kosong", Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }

    }

    private fun initView() {
        binding.apply {
            args.ticketData.apply {
                bandara.text = airport?.airportName
                timeAriv.text = arrivalDate
                timeDep.text = departureDate
                dateDepEdit.text = airport?.airportLocation
                harga.text = "Rp."+price.toString()
                FlightCode.text = "AERO"+"PLN"+id+"4A3U1"
            }

            }
        }

    private fun validateInput(): Boolean {
        var isValid = true
        val name = binding.edtName.text.toString().trim()
        val nik = binding.inputNik.text.toString().trim()

        if (name.isEmpty()) {
            isValid = false
            binding.edtName.error = "Passenger Name must not be empty"
        }
        if (nik.isEmpty()) {
            isValid = false
            binding.inputNik.error = "NIK Name must not be empty"

        }
        if (nik.length < 16) {
            isValid = false
            Toast.makeText(
                requireContext(),
                "NIK should be at least 16 characters",
                Toast.LENGTH_SHORT
            ).show()
        }
        return isValid
    }

    private fun parseFormIntoEntity(name: String, nik: String): CreatePassengerRequestBody {
        return CreatePassengerRequestBody(name, nik)
    }
    }
