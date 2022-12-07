package com.example.fpandc13.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fpandc13.R
import com.example.fpandc13.data.local.datastore.DataStoreManager
import com.example.fpandc13.databinding.FragmentLoginBinding
import com.example.fpandc13.ui.activity.HomeActivity

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var dataStoreManager: DataStoreManager

    private val existUsername = listOf<String>("shawn","peter","raul","mendes")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataStoreManager = DataStoreManager(requireContext())
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory(dataStoreManager))[LoginViewModel::class.java]

        binding.tvCreateAccount.setOnClickListener { openRegister() }
        binding.btnLogin.setOnClickListener { toLogin() }
    }

    private fun toLogin() {
        val email = binding.etEmailLogin.text.toString()
        val password = binding.etPasswordLogin.text.toString()

        var emailAccount: String? = ""
        var passwordAccount: String? = ""

        loginViewModel.getEmail().observe(viewLifecycleOwner) {
            emailAccount = it.toString()
        }

        loginViewModel.getPassword().observe(viewLifecycleOwner) {
            passwordAccount = it.toString()
        }

        if (email == emailAccount && password == passwordAccount) {
            loginViewModel.statusLogin(true)
            activity?.let {
                val intent = Intent(it, HomeActivity::class.java)
                it.startActivity(intent)}
        } else {
            Toast.makeText(requireContext(), "Akun tidak sesuai", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        loginViewModel.getLoginStatus().observe(viewLifecycleOwner) {
            if (it == true) {
                activity?.let { it ->
                    val intent = Intent(it, HomeActivity::class.java)
                    it.startActivity(intent)}
            } else {
                requireContext()
            }
        }
    }

    private fun openRegister() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun validateLoginFragmentInput(
        username: String,
        password: String
    ): Boolean {
        if (username.isEmpty() || password.isEmpty()){
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
        return true
    }
}
