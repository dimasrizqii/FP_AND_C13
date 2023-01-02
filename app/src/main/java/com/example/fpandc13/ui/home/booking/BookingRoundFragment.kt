package com.example.fpandc13.ui.home.booking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fpandc13.R
import com.example.fpandc13.data.network.models.booking.create.CreateBookingRequestBody
import com.example.fpandc13.data.network.models.passenger.create.CreatePassengerRequestBody
import com.example.fpandc13.databinding.FragmentBookingRoundBinding
import com.example.fpandc13.ui.home.datapassenger.DataPassengerViewModel
import com.example.fpandc13.ui.home.profile.ProfileViewModel
import com.example.fpandc13.ui.login.LoginViewModel
import com.example.fpandc13.wrapper.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class BookingRoundFragment : Fragment() {


    private var _binding: FragmentBookingRoundBinding? = null
    private val binding get() = _binding!!

    private val DataViewModel: DataPassengerViewModel by viewModels()
    private val userViewModel : ProfileViewModel by viewModels()
    private val authViewModel : LoginViewModel by viewModels()
    private val args: BookingRoundFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { observeDataUser()

        // Inflate the layout for this fragment
        _binding = FragmentBookingRoundBinding.inflate(inflater,container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeQueryResult()
        binding.btnSearch.setOnClickListener { CreateBooking() }

    }

    private fun observeQueryResult() {
        authViewModel.getDataStoreToken().observe(viewLifecycleOwner) {
            userViewModel.GetProfileUser("Bearer $it")
        }
        DataViewModel.CreateBooking.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success ->{
                    Toast.makeText(requireContext(), "${it.data?.message}", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_bookingRoundFragment_to_paymentFragment)
                    Toast.makeText(requireContext(), "Booking Berhasil ", Toast.LENGTH_LONG).show()
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

    private fun observeDataUser(){
        authViewModel.getDataStoreToken().observe(viewLifecycleOwner) {
            userViewModel.GetProfileUser("Bearer $it")
        }
        userViewModel.GetProfileUserResponse.observe(viewLifecycleOwner) {
            when(it){
                is Resource.Success ->{
                    Log.d("GetUserProfileResponse", it.data.toString())
                    viewId()
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


    fun viewId(){
        DataViewModel.getDataStoreId().observe(viewLifecycleOwner) {
            binding.textIdUser.text = "$it"
        }
        DataViewModel.getDataStoreTicket().observe(viewLifecycleOwner) {
            binding.textIdTicket.text = "$it"

        }
        DataViewModel.getDataStorePassenger().observe(viewLifecycleOwner) {
            binding.textIdPassenger.text = "$it"
        }

      }

    fun CreateBooking() {
        binding.apply {

            val IdTicket: String = textIdTicket.getText().toString()
            val IdUser: String = textIdUser.getText().toString()
            val IdPassenger: String = textIdPassenger.getText().toString()

            val idPass = IdPassenger.toInt()
            val idTick = IdTicket.toInt()
            val idUser = IdUser.toInt()
            val total : Int = 1
            authViewModel.getDataStoreToken().observe(viewLifecycleOwner) {
                DataViewModel.postNewBooking("Bearer $it" , parseFormIntoEntity(idPass,idTick,idUser,total))
            }

            }


        }

    private fun parseFormIntoEntity(idPass: Int,idTicket: Int, idUser: Int, total : Int): CreateBookingRequestBody {
        return CreateBookingRequestBody(idPass,idTicket,idUser,total)
    }
}







