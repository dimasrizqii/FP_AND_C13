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
import com.bumptech.glide.Glide
import com.example.fpandc13.R
import com.example.fpandc13.databinding.FragmentProfileBinding
import com.example.fpandc13.ui.activity.Home.HomeActivity
import com.example.fpandc13.ui.activity.MainActivity
import com.example.fpandc13.ui.login.LoginViewModel
import com.example.fpandc13.wrapper.Resource
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaType
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
    private val userViewModel : LoginViewModel by viewModels()

    private val existUsername = listOf("shawn","peter","raul","mendes")
    private val existName = listOf("shawn","peter","raUL")

    private var imageUri: Uri? = null
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
        getProfile()
        updateProfile()

        binding.logout.setOnClickListener {
            toLogOut()
        }

        binding.btnBack.setOnClickListener {
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

    private fun updateProfile(){
        userViewModel.getDataStoreToken().observe(viewLifecycleOwner) {
            viewModel.getProfileUser("Bearer $it")
        }

        binding.btnUpdate.setOnClickListener {
            val firstName = binding.edtFirtsName.text.toString().trim()
                .toRequestBody("multipart/form-data".toMediaType())
            val lastName = binding.edtLastName.text.toString().trim()
                .toRequestBody("multipart/form-data".toMediaType())
            val username = binding.edtUsername.text.toString().trim()
                .toRequestBody("multipart/form-data".toMediaType())
            val address = binding.edtAddress.text.toString().trim()
                .toRequestBody("multipart/form-data".toMediaType())
            val phone = binding.edtNomor.text.toString().trim()
                .toRequestBody("multipart/form-data".toMediaType())
            userViewModel.getDataStoreToken().observe(viewLifecycleOwner) {
                viewModel.updateUser(firstName,lastName,username,phone,address,imageMultiPart!!,"Bearer $it")
            }
            Toast.makeText(requireContext(), "Update Success", Toast.LENGTH_SHORT).show()
        }

        viewModel.user.observe(viewLifecycleOwner) {
            binding.apply {
                if (it != null) {
                    edtFirtsName.setText(it.profile?.firstName)
                    edtLastName.setText(it.profile?.lastName)
                    edtUsername.setText(it.profile?.username)
                    edtNomor.setText(it.profile?.phoneNumber)
                    edtAddress.setText(it.profile?.address)
                }
            }
        }
        openGallery()
    }


    private fun getProfile() {
        userViewModel.getDataStoreToken().observe(viewLifecycleOwner) {
            viewModel.getProfileUser("Bearer $it")
        }

        viewModel.user.observe(viewLifecycleOwner) {
            binding.apply {
                if (it != null) {
                    edtFirtsName.setText(it.profile?.firstName.toString())
                    edtLastName.setText(it.profile?.lastName.toString())
                    edtUsername.setText(it.profile?.username.toString())
                    edtAddress.setText(it.profile?.address.toString())
                    edtNomor.setText(it.profile?.phoneNumber.toString())
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
        viewModel.getProfileUserResponse.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success ->{
                    Log.d("GetUserProfileResponse", it.data.toString())
                    binding.apply {
                        edtFirtsName.setText(it.data?.profile?.firstName.toString())
                        edtLastName.setText(it.data?.profile?.lastName.toString())
                        edtUsername.setText(it.data?.profile?.username.toString())
                        edtAddress.setText(it.data?.profile?.address.toString())
                        edtNomor.setText(it.data?.profile?.phoneNumber.toString())
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

    private fun openGallery() {
        binding.profileImage.setOnClickListener {
            changePicture.launch("image/*")
        }
    }

    private val changePicture =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                val contentResolver: ContentResolver = requireContext().contentResolver
                val type = contentResolver.getType(it)
                imageUri = it

                val fileNameimg = "${System.currentTimeMillis()}.png"
                binding.profileImage.setImageURI(it)
                Toast.makeText(requireContext(), "$imageUri", Toast.LENGTH_SHORT).show()

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