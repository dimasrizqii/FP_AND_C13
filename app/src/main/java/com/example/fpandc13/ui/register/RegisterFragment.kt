package com.example.fpandc13.ui.register

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fpandc13.R
import com.example.fpandc13.databinding.FragmentRegisterBinding
import com.example.fpandc13.data.network.models.auth.register.RegisterRequestBody
import com.example.fpandc13.wrapper.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel : RegisterViewModel by viewModels()

    private val existUsername = listOf<String>("shawn","peter","raul","mendes")
    private val existEmail = listOf<String>("shawn@test.com","peter@test.com","raul@test.com","mendes@test.com")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvHaveAccount.setOnClickListener { openLogin() }
        binding.btnRegister.setOnClickListener { registerUser() }
        observeData()
    }

    private fun openLogin() {
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }

    private fun registerUser() {
        if (validateInput()) {
            applyRegister()
        }
    }

    private fun registerUser(firstName : String ,lastName : String ,email: String,username: String, password: String, address: String, phone: String) {
        viewModel.postRegisterUser(RegisterRequestBody(firstName = firstName, lastName = lastName,email = email,username = username, password = password, address = address, phoneNumber = phone ))
        Log.d("register", RegisterRequestBody(firstName = firstName, lastName = lastName,email = email,username = username, password = password, address = address, phoneNumber = phone).toString())
    }

    private fun observeData() {
        viewModel.postRegisterUserResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    if (it.data?.message.equals("User created successfully")) {
                        Toast.makeText(requireContext(), it.data?.message, Toast.LENGTH_LONG).show()
                        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                        Toast.makeText(requireContext(), "Register User Success, Please Login", Toast.LENGTH_SHORT).show()
                        Log.d("registerresponse", it.data?.message.toString())
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "Register Gagal Silahkan Periksa Jaringan Internet Anda", Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }

    }

    private fun applyRegister() {
        val firstName = binding.etFirstRegister.text.toString()
        val lastName = binding.etLastRegister.text.toString()
        val email = binding.etEmailRegister.text.toString()
        val username = binding.etUsernameRegister.text.toString()
        val password = binding.etPasswordRegister.text.toString()
        val address = binding.etAddressRegister.text.toString()
        val phone = binding.etPhoneRegister.text.toString()

        registerUser(firstName,lastName,email,username,password,address,phone)

    }

    private fun validateInput(): Boolean {
        var isValid = true
        val email = binding.etEmailRegister.text.toString().trim()
        val username = binding.etUsernameRegister.text.toString().trim()
        val password = binding.etPasswordRegister.text.toString().trim()
        val address = binding.etAddressRegister.text.toString().trim()
        val phone = binding.etPhoneRegister.text.toString().trim()


        if (email.isEmpty()) {
            isValid = false
            binding.etEmailRegister.error = "Email must not be empty"
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            isValid = false
            binding.etEmailRegister.error = "Invalid email"
        }
        if (password.isEmpty()) {
            isValid = false
            Toast.makeText(requireContext(), "Password must not be empty", Toast.LENGTH_SHORT)
                .show()
        }
        if (username.isEmpty()) {
            isValid = false
            Toast.makeText(requireContext(), "Username must not be empty", Toast.LENGTH_SHORT)
                .show()
        }
        if (address.isEmpty()) {
            isValid = false
            Toast.makeText(requireContext(), "Address must not be empty", Toast.LENGTH_SHORT)
                .show()
        }
        if (phone.isEmpty()) {
            isValid = false
            Toast.makeText(requireContext(), "Phone Number must not be empty", Toast.LENGTH_SHORT)
                .show()
        }
        if (password.length < 6) {
            isValid = false
            Toast.makeText(
                requireContext(),
                "Password should be at least 6 characters",
                Toast.LENGTH_SHORT
            ).show()
        }
        return isValid
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun validateRegisterFragmentInput(
        username: String,
        password: String,
        repeatPassword: String,
        email: String

    ): Boolean {
        if (username.isEmpty() || password.isEmpty() || email.isEmpty()){
            return false
        }

        if (username in existUsername) {
            return false
        }

        if (password.length < 6) {
            false
        }

        if (password.length > 50) {
            false
        }
        if (password != repeatPassword){
            return false
        }

        if (email in existEmail) {
            return false
        }

        return true
    }

}