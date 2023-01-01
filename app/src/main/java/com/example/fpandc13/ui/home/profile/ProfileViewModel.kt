package com.example.fpandc13.ui.home.profile

import androidx.lifecycle.*
import com.example.fpandc13.data.local.preference.UserDataStoreManager
import com.example.fpandc13.data.network.models.auth.login.LoginRequestBody
import com.example.fpandc13.data.network.models.auth.login.LoginResponse
import com.example.fpandc13.data.network.models.auth.profile.get.GetUserProfileResponse
import com.example.fpandc13.data.network.models.auth.profile.get.Profile
import com.example.fpandc13.data.network.models.auth.profile.update.UpdateProfileResponse
import com.example.fpandc13.data.network.service.auth.AeroplaneAuthApiInterface
import com.example.fpandc13.data.repository.AuthRepository
import com.example.fpandc13.data.repository.UserRepository
import com.example.fpandc13.wrapper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val dataStoreManager: UserDataStoreManager, private val userRepository: UserRepository, private val AuthRepository: AuthRepository, private val ApiClient: AeroplaneAuthApiInterface ) : ViewModel() {

    private var _profileResponse = MutableLiveData<Resource<GetUserProfileResponse>>()
    val getProfileUserResponse: LiveData<Resource<GetUserProfileResponse>> get() = _profileResponse
    private var _profilePutResponse = MutableLiveData<Resource<UpdateProfileResponse>>()
    val putProfileUserResponse: LiveData<Resource<UpdateProfileResponse>> get() = _profilePutResponse
    private var _postLoginUserResponse = MutableLiveData<Resource<LoginResponse>>()
    val postLoginUserResponse: LiveData<Resource<LoginResponse>> get() = _postLoginUserResponse

    private val _user: MutableLiveData<GetUserProfileResponse> = MutableLiveData()
    val user: LiveData<GetUserProfileResponse?> get() = _user
    private val _update: MutableLiveData<Profile?> = MutableLiveData()
    val update: LiveData<Profile?> get() = _update

    suspend fun getProfile(token: String) {
        userRepository.getProfileData(token)
    }
    fun statusLogin(isLogin: Boolean) {
        viewModelScope.launch {
            dataStoreManager.statusLogin(isLogin)
        }
    }

    fun getLoginStatus(): LiveData<Boolean> {
        return dataStoreManager.getLoginStatus().asLiveData()
    }

    fun getProfileUser(token : String) {
        viewModelScope.launch(Dispatchers.IO) {
            val profileGet = userRepository.getProfileData(token)
            viewModelScope.launch(Dispatchers.Main) {
                _profileResponse.postValue(profileGet)
            }
        }
    }

    fun updateUser(
        firstName : RequestBody,
        lastName : RequestBody,
        userName : RequestBody,
        address: RequestBody,
        phone_number: RequestBody,
        image: MultipartBody.Part,
        token: String
    ){
        ApiClient.updateUser(firstName,lastName,userName,phone_number,address,image,token)
            .enqueue(object : Callback<Profile> {
                override fun onResponse(
                    call: Call<Profile>,
                    response: Response<Profile>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            _update.postValue(responseBody)
                        }
                    }
                }

                override fun onFailure(call: Call<Profile>, t: Throwable) {
                    _update.postValue(null)
                }
            })
    }

    fun putProfileUser(token : String) {
        viewModelScope.launch(Dispatchers.IO) {
            val profilePut = userRepository.putProfileData(token)
            viewModelScope.launch(Dispatchers.Main) {
                _profilePutResponse.postValue(profilePut)
            }
        }
    }

    fun postLoginUser(loginRequestBody: LoginRequestBody) {
        viewModelScope.launch(Dispatchers.IO) {
            val loginResponse = AuthRepository.postLoginUser(loginRequestBody)
            viewModelScope.launch(Dispatchers.Main) {
                _postLoginUserResponse.postValue(loginResponse)
            }
        }
    }






}