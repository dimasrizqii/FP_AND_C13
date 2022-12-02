package com.example.fpandc13.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fpandc13.R
import com.example.fpandc13.data.local.datastore.DataStoreManager
import com.example.fpandc13.databinding.FragmentRegisterBinding
import com.example.fpandc13.ui.login.LoginViewModelFactory

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataStoreManager = DataStoreManager(requireContext())
        registerViewModel = ViewModelProvider(this, LoginViewModelFactory(dataStoreManager))[RegisterViewModel::class.java]


        binding.tvHaveAccount.setOnClickListener { openLogin() }
        binding.btnRegister.setOnClickListener { toCreateAccount() }
    }

    private fun toCreateAccount() {
        val email = binding.etEmailRegister.text.toString()
        val password = binding.etPasswordRegister.text.toString()
        val phoneNumber = binding.etPhoneNumberRegister.text.toString()

        registerViewModel.saveAccount(email, password, phoneNumber)
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }

    private fun openLogin() {
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}