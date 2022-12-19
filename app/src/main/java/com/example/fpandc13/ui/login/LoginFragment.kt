package com.example.fpandc13.ui.login

import android.content.Intent
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
import com.example.fpandc13.databinding.FragmentLoginBinding
import com.example.fpandc13.models.auth.login.LoginRequestBody
import com.example.fpandc13.ui.activity.HomeActivity
import com.example.fpandc13.ui.activity.MainActivity
import com.example.fpandc13.wrapper.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

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

        viewModel.getLoginStatus().observe(viewLifecycleOwner) {
            if (it == true) {
                activity?.let { it ->
                    val intent = Intent(it, HomeActivity::class.java)
                    it.startActivity(intent)}
            } else {
                requireContext()
            }
        }

        observeData()

        binding.tvCreateAccount.setOnClickListener { openRegister() }
        binding.btnLogin.setOnClickListener { loginUser() }
    }

    private fun observeData() {
        viewModel.postLoginUserResponse.observe(viewLifecycleOwner) {
            binding.etEmailLogin.isEnabled = true
            binding.etPasswordLogin.isEnabled = true

            when (it) {
                is Resource.Success -> {
                    Toast.makeText(requireContext(), " ${it.data?.message}", Toast.LENGTH_LONG).show()
                    Log.d("loginResponse", it.data.toString())
                    viewModel.statusLogin(true)
                    navigateToHome()
                }
                is Resource.Error -> {
                }
                else -> {}
            }
        }
        viewModel.getUserLoginStatus().observe(viewLifecycleOwner) {
            Log.d("getlogin", it.toString())
            if (it) {
                navigateToHome()
            }
        }
    }

    private fun navigateToHome() {
        viewModel.setUserLogin(true)
        activity?.let { it ->
            val intent = Intent(it, HomeActivity::class.java)
            it.startActivity(intent)}
    }

    private fun loginUser() {
        if (validateInput()) {
            val email = binding.etEmailLogin.text.toString().trim()
            val password = binding.etPasswordLogin.text.toString().trim()

            binding.etEmailLogin.isEnabled = false
            binding.etPasswordLogin.isEnabled = false
            viewModel.postLoginUser(parseFormIntoEntity(email, password))
        }
    }

    private fun parseFormIntoEntity(email: String, password: String): LoginRequestBody {
        return LoginRequestBody(email, password)
    }

    private fun validateInput(): Boolean {
        var isValid = true
        val email = binding.etEmailLogin.text.toString().trim()
        val password = binding.etPasswordLogin.text.toString().trim()

        if (email.isEmpty()) {
            isValid = false
            binding.etEmailLogin.error = "Email must not be empty"
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            isValid = false
            binding.etEmailLogin.error = "Invalid email"
        }
        if (password.isEmpty()) {
            isValid = false
            Toast.makeText(requireContext(), "Password must not be empty", Toast.LENGTH_SHORT)
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



//    private fun toLogin() {
//        val email = binding.etEmailLogin.text.toString()
//        val password = binding.etPasswordLogin.text.toString()
//
//        var emailAccount: String? = ""
//        var passwordAccount: String? = ""
//
//        loginViewModel.getEmail().observe(viewLifecycleOwner) {
//            emailAccount = it.toString()
//        }
//
//        loginViewModel.getPassword().observe(viewLifecycleOwner) {
//            passwordAccount = it.toString()
//        }
//
//        if (email == emailAccount && password == passwordAccount) {
//            loginViewModel.statusLogin(true)
//            activity?.let {
//                val intent = Intent(it, HomeActivity::class.java)
//                it.startActivity(intent)}
//        } else {
//            Toast.makeText(requireContext(), "Akun tidak sesuai", Toast.LENGTH_SHORT).show()
//        }
//    }

//    override fun onResume() {
//        super.onResume()
//        loginViewModel.getLoginStatus().observe(viewLifecycleOwner) {
//            if (it == true) {
//                activity?.let { it ->
//                    val intent = Intent(it, HomeActivity::class.java)
//                    it.startActivity(intent)}
//            } else {
//                requireContext()
//            }
//        }
//    }

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
