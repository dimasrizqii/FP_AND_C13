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
import com.example.fpandc13.ui.home.profile.ProfileViewModel
import com.example.fpandc13.ui.login.LoginViewModel
import com.example.fpandc13.wrapper.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DataPassengerFragment : Fragment() {


    private var _bindingFrag: FragmentDataPassengerBinding? = null
    private val binding get() = _bindingFrag!!

    private val args: DataPassengerFragmentArgs by navArgs()
    private val viewModel: DataPassengerViewModel by viewModels()
    private val userViewModel : ProfileViewModel by viewModels()
    private val authViewModel : LoginViewModel by viewModels()


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
        observeDataUser()
        initView()
        binding.btnCheckout.setOnClickListener { CreatePassenger() }

    }




    private fun CreatePassenger() {
        if (validateInput()) {
            val nama = binding.edtName.text.toString().trim()
            val nik = binding.inputNik.text.toString().trim()

            binding.edtName.isEnabled = false
            binding.inputNik.isEnabled = false
            authViewModel.getDataStoreToken().observe(viewLifecycleOwner) {
                viewModel.postPassengers("Bearer $it", parseFormIntoEntity(nik, nama))}
            }

    }

    private fun observeDataUser(){
        authViewModel.getDataStoreToken().observe(viewLifecycleOwner) {
            userViewModel.GetProfileUser("Bearer $it")
        }
        userViewModel.GetProfileUserResponse.observe(viewLifecycleOwner) {
            when(it){
                is Resource.Success ->{
                    Log.d("GetUserProfileResponse", it.data.toString())
                    viewModel.SaveUserId("${it.data?.profile?.id}")
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "Reload Gagal : ObserveGet", Toast.LENGTH_LONG).show()

                }
                is Resource.Empty-> {
                    Toast.makeText(requireContext(), "Field : Empty", Toast.LENGTH_LONG).show()
                }
                else -> {}
            }

        }
    }
    private fun observeQueryResult() {
        authViewModel.getDataStoreToken().observe(viewLifecycleOwner) {
            userViewModel.GetProfileUser("Bearer $it")
        }
        viewModel.CreatePassenger.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success ->{
                    viewModel.SavePassengerId("${it.data?.newPassenger?.id}")
                    Toast.makeText(requireContext(), "${it.data?.newPassenger?.id}", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_dataPassengerFragment_to_bookingRoundFragment)
                    Log.d("VerifyResponse", it.data.toString())
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "Error : Kesalahan ", Toast.LENGTH_LONG).show()
                }
                is Resource.Empty -> {
                    Toast.makeText(requireContext(), "Empty : Kosong", Toast.LENGTH_LONG).show()
                }
                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_LONG).show()
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
                viewModel.SaveTicketId(id.toString())
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

    private fun parseFormIntoEntity(nik: String, name: String): CreatePassengerRequestBody {
        return CreatePassengerRequestBody(nik, name)
    }
    }


//
//        viewModel.LiveDataTicket.observe(viewLifecycleOwner) { result ->
//            Log.d(ContentValues.TAG, "Fragment -> ${result}")
//        }
