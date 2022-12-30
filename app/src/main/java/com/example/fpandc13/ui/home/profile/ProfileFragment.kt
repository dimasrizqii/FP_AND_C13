package com.example.fpandc13.ui.home.profile

import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.fpandc13.R
import com.example.fpandc13.databinding.FragmentProfileBinding
import com.example.fpandc13.ui.activity.Home.HomeActivity
import com.example.fpandc13.ui.activity.MainActivity
import com.example.fpandc13.ui.login.LoginViewModel
import com.example.fpandc13.wrapper.Resource
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

@AndroidEntryPoint
class ProfileFragment : Fragment() {



    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()
    private val UserViewModel : LoginViewModel by viewModels()

    private val existUsername = listOf<String>("shawn","peter","raul","mendes")
    private val existName = listOf<String>("shawn","peter","raUL")

    private var image_uri: Uri? = null
    private var imageFile: File? = null
    private var imageMultiPart: MultipartBody.Part? = null

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
        GetProfile()
        UpdateProfile()


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

    private fun UpdateProfile(){
        UserViewModel.getDataStoreToken().observe(viewLifecycleOwner) {
            viewModel.GetProfileUser("Bearer $it")
        }

        binding.btnUpdate.setOnClickListener {

            val firstName = binding.etFirstRegister.text.toString().trim()
                .toRequestBody("multipart/form-data".toMediaType())
            val lastName = binding.etLastRegister.text.toString().trim()
                .toRequestBody("multipart/form-data".toMediaType())
            val username = binding.etUsernameRegister.text.toString().trim()
                .toRequestBody("multipart/form-data".toMediaType())
            val address = binding.etAddressRegister.text.toString().trim()
                .toRequestBody("multipart/form-data".toMediaType())
            val phone = binding.etPhoneRegister.text.toString().trim()
                .toRequestBody("multipart/form-data".toMediaType())
            UserViewModel.getDataStoreToken().observe(viewLifecycleOwner) {
                viewModel.updateUser(firstName,lastName,username,phone,address,imageMultiPart!!,"Bearer $it")
            }
            Toast.makeText(requireContext(), "Update Success", Toast.LENGTH_SHORT).show()
        }

        viewModel.user.observe(viewLifecycleOwner) {
            binding.apply {
                if (it != null) {
                    etFirstRegister.setText(it.profile?.firstName)
                    etLastRegister.setText(it.profile?.lastName)
                    etUsernameRegister.setText(it.profile?.username)
                    etPhoneRegister.setText(it.profile?.phoneNumber)
                    etAddressRegister.setText(it.profile?.address)
                }
            }
        }
        openGallery()
    }


    private fun GetProfile() {
        UserViewModel.getDataStoreToken().observe(viewLifecycleOwner) {
            viewModel.GetProfileUser("Bearer $it")
        }

        viewModel.user.observe(viewLifecycleOwner) {
            binding.apply {
                if (it != null) {
                    etFirstRegister.setText("${it.profile?.firstName.toString()}")
                    etLastRegister.setText("${it.profile?.lastName.toString()}")
                    etUsernameRegister.setText("${it.profile?.username.toString()}")
                    etAddressRegister.setText("${it.profile?.address.toString()}")
                    etPhoneRegister.setText("${it.profile?.phoneNumber.toString()}")
                    Glide.with(requireContext())
                        .load(it.profile?.photo)
                        .circleCrop()
                        .into(binding.profileImage)
                }
            }
        }

        viewModel.postLoginUserResponse.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success ->{
                    val token = "${it.data?.token}"
                    if (token != ""){
                        Log.d("TokenResponse", it.data.toString())
                    }

                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "Reload Gagal : GetProfile", Toast.LENGTH_LONG).show()
                }
                is Resource.Empty-> {
                    Toast.makeText(requireContext(), "Field : Empty", Toast.LENGTH_LONG).show()
                }

                else -> {} }
        }}

    private fun observeGet(){
        viewModel.GetProfileUserResponse.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success ->{
                    Log.d("GetUserProfileResponse", it.data.toString())
                    binding.apply {
                        etFirstRegister.setText("${it.data?.profile?.firstName.toString()}")
                        etLastRegister.setText("${it.data?.profile?.lastName.toString()}")
                        etUsernameRegister.setText("${it.data?.profile?.username.toString()}")
                        etAddressRegister.setText("${it.data?.profile?.address.toString()}")
                        etPhoneRegister.setText("${it.data?.profile?.phoneNumber.toString()}")
                        Glide.with(requireContext())
                            .load(it.data?.profile?.photo)
                            .circleCrop()
                            .into(binding.profileImage)

                    }
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

    fun openGallery() {
        binding.profileImage.setOnClickListener {
            changePicture.launch("image/*")
        }
    }

    private val changePicture =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                val contentResolver: ContentResolver = requireContext().contentResolver
                val type = contentResolver.getType(it)
                image_uri = it

                val fileNameimg = "${System.currentTimeMillis()}.png"
                binding.profileImage.setImageURI(it)
                Toast.makeText(requireContext(), "$image_uri", Toast.LENGTH_SHORT).show()

                val tempFile = File.createTempFile("C13-", fileNameimg, null)
                imageFile = tempFile
                val inputstream = contentResolver.openInputStream(uri)
                tempFile.outputStream().use { result ->
                    inputstream?.copyTo(result)
                }
                val requestBody: RequestBody = tempFile.asRequestBody(type?.toMediaType())
                imageMultiPart =
                    MultipartBody.Part.createFormData("image", tempFile.name, requestBody)
            }
        }
}