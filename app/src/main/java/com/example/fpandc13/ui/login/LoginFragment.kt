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
import com.example.fpandc13.data.network.models.auth.login.LoginRequestBody
import com.example.fpandc13.data.network.models.auth.profile.get.Data
import com.example.fpandc13.data.network.models.auth.profile.update.UpdateProfileResponse
import com.example.fpandc13.data.network.models.auth.verify.VerifyRequestBody
import com.example.fpandc13.databinding.FragmentProfileBinding
import com.example.fpandc13.ui.activity.Home.HomeActivity
import com.example.fpandc13.ui.home.profile.ProfileViewModel
import com.example.fpandc13.wrapper.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private var _bindingProfile: FragmentProfileBinding? = null
    private val bindingProfile get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()
    private val profileViewModel : ProfileViewModel by viewModels()

    private val existUsername = listOf("shawn","peter","raul","mendes")

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
        observeVerify()
        observeDataLogin()

        binding.tvCreateAccount.setOnClickListener { openRegister() }
        binding.btnLogin.setOnClickListener (){
            VerifyUser()
            loginUser()
        }
    }

    private fun VerifyUser() {
            viewModel.postLoginUserResponse.observe(viewLifecycleOwner){
                when(it){
                    is Resource.Success ->{

                        val email = binding.etEmailLogin.text.toString().trim()
                        val password = binding.etPasswordLogin.text.toString().trim()
                        val token = "${it.data?.token}"



                        binding.etEmailLogin.isEnabled = false
                        binding.etPasswordLogin.isEnabled = false

                        if (token != ""){
                            //ini untuk set tokennya ke datastore
                            viewModel.setUserToken(token)
                            viewModel.SaveUserToken(token)
//                            //ini kemarin nyoba authorization langsung pake token yang aku ambil dari val token
//                            viewModel.GetProfileUser("Bearer"+" "+token)
                            Toast.makeText(requireContext(), "Token Set", Toast.LENGTH_LONG).show()


                        } else {
                            Toast.makeText(requireContext(), "token gagal di set", Toast.LENGTH_LONG).show()
                        }

                        viewModel.postVerifyUser(parseFormIntoEntityVerify(email,password,token))

                    }

                    else -> {} }
            }}


    private fun loginUser() {
        if (validateInput()) {

            val email = binding.etEmailLogin.text.toString().trim()
            val password = binding.etPasswordLogin.text.toString().trim()

            binding.etEmailLogin.isEnabled = false
            binding.etPasswordLogin.isEnabled = false
            viewModel.postLoginUser(parseFormIntoEntity(email, password))

        }
    }

    private fun observeVerify(){
        viewModel.postVerifyUserResponse.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success ->{
                    Toast.makeText(requireContext(), "${it.data?.message}", Toast.LENGTH_LONG).show()
                    Log.d("VerifyResponse", it.data.toString())
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "Verifikasi Gagal Silahkan Periksa Jaringan Internet Anda", Toast.LENGTH_LONG).show()
                }
                is Resource.Empty -> {
                    Toast.makeText(requireContext(), "Verifikasi Gagal : Kosong", Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }

    }

    private fun observeDataLogin() {
        viewModel.postLoginUserResponse.observe(viewLifecycleOwner) {
            binding.etEmailLogin.isEnabled = true
            binding.etPasswordLogin.isEnabled = true
            when (it) {
                is Resource.Success -> {
                    Toast.makeText(requireContext(), "${it.data?.message}", Toast.LENGTH_LONG).show()
                    Log.d("loginResponse", it.data.toString())
                    viewModel.statusLogin(true)
                    navigateToHome()
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "Login Gagal Silahkan Periksa Jaringan Internet Anda", Toast.LENGTH_LONG).show()

                }
                is Resource.Empty -> {
                    Toast.makeText(requireContext(), "Empty", Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }
        //datastore
        viewModel.getUserLoginStatus().observe(viewLifecycleOwner) {
            Log.d("getlogin", it.toString())
            if (it) {
                navigateToHome()
            }
        }
    }

    private fun observeStatus(){
        viewModel.PutProfileUserResponse.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success ->{
                    val status = "${it.data?.status}".equals("success").toString()
                    Toast.makeText(requireContext(), "${it.data?.status}", Toast.LENGTH_LONG).show()
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()

                }
                is Resource.Empty -> {
                    Toast.makeText(requireContext(), "Null", Toast.LENGTH_LONG).show()

                }
                else -> {}
            }
        }
        viewModel.GetProfileUserResponse.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success ->{
                    Toast.makeText(requireContext(), "${it.data?.profile}", Toast.LENGTH_LONG).show()
                    Log.d("GetResponse", it.data.toString())
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "Error2", Toast.LENGTH_LONG).show()

                }
                is Resource.Empty -> {
                    Toast.makeText(requireContext(), "Null2", Toast.LENGTH_LONG).show()

                }

                else -> {}
            }

        }
    }


    private fun navigateToHome() {
        viewModel.setUserLogin(true)
        activity?.let { it ->
            val intent = Intent(it, HomeActivity::class.java)
            it.startActivity(intent)}
    }



    private fun parseFormIntoEntity(email: String, password: String): LoginRequestBody {
        return LoginRequestBody(email, password)
    }
    private fun parseFormIntoEntityVerify(email: String, password: String, token : String): VerifyRequestBody{
        return VerifyRequestBody(email,password,token)
    }
//    private fun parseFormIntoEntityStatus(data: Data): UpdateProfileResponse{
//        return UpdateProfileResponse()
//    }

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
