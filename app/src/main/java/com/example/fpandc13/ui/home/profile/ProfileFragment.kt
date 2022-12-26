package com.example.fpandc13.ui.home.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import com.example.fpandc13.R
import com.example.fpandc13.data.network.models.auth.profile.get.Data
import com.example.fpandc13.databinding.FragmentProfileBinding
import com.example.fpandc13.ui.activity.Home.HomeActivity
import com.example.fpandc13.ui.activity.MainActivity
import com.example.fpandc13.wrapper.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()

    private val existUsername = listOf<String>("shawn","peter","raul","mendes")
    private val existName = listOf<String>("shawn","peter","raUL")

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
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeGet()

        binding.logout.setOnClickListener(){
            toLogOut()
        }

        binding.btnBack.setOnClickListener(){
            activity?.let { it ->
                val intent = Intent(it, HomeActivity::class.java)
                it.startActivity(intent)}
        }
    }

    private fun toLogOut() {
        val option = NavOptions.Builder()
            .setPopUpTo(R.id.profileFragment, true)
            .build()

        viewModel.statusLogin(false)
        viewModel.getLoginStatus().observe(viewLifecycleOwner) {
            if (it == true) {
                activity?.let { it ->
                    val intent = Intent(it, MainActivity::class.java)
                    it.startActivity(intent)}
            } else {
                requireContext()
            }
        }
    }

    private fun GetProfile() {
        viewModel.postLoginUserResponse.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success ->{
                    viewModel.GetProfileUser("${it.data?.token}")
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "Reload Gagal Silahkan Periksa Jaringan Internet Anda", Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }
        viewModel.getProfileUserResponse.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success ->{
//                  viewModel.GetProfileUser()
                }
                else -> {}
            }
        }
    }
    private fun parseFormIntoEntity(token : String): Data {
        return Data(
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "")
    }

    private fun observeGet(){
        viewModel.getProfileUserResponse.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success ->{
                    Toast.makeText(requireContext(), "${it.data?.data}", Toast.LENGTH_LONG).show()
                    Log.d("VerifyResponse", it.data.toString())
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "Reload Gagal Silahkan Periksa Jaringan Internet Anda", Toast.LENGTH_LONG).show()
                }
                else -> {}
            }

        }
    }

    fun validateProfileFragmentInput(
        name: String,
        username: String,
        email: String,
        birthday: String,
        phone: String

    ): Boolean {
        if (name.isEmpty() || username.isEmpty() || email.isEmpty() || birthday.isEmpty() || phone.isEmpty() ){
            return false
        }

        if (username in existUsername) {
            return false
        }

        if (name in existName) {
            return false
        }


        return true
    }
}