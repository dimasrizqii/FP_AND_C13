package com.example.fpandc13.ui.home.profile

import androidx.lifecycle.*
import com.example.fpandc13.data.local.preference.UserDataStoreManager
import com.example.fpandc13.data.network.models.auth.login.LoginRequestBody
import com.example.fpandc13.data.network.models.auth.login.LoginResponse
import com.example.fpandc13.data.network.models.auth.profile.get.GetProfileResponse
import com.example.fpandc13.data.network.models.auth.profile.update.UpdateProfileResponse
import com.example.fpandc13.data.network.models.auth.verify.VerifyRequestBody
import com.example.fpandc13.data.network.models.auth.verify.VerifyResponse
import com.example.fpandc13.data.repository.AuthRepository
import com.example.fpandc13.data.repository.UserRepository
import com.example.fpandc13.wrapper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(val dataStoreManager: UserDataStoreManager , private val userRepository: UserRepository , private val AuthRepository: AuthRepository) : ViewModel() {

    private var _ProfileResponse = MutableLiveData<Resource<GetProfileResponse>>()
    val GetProfileUserResponse: LiveData<Resource<GetProfileResponse>> get() = _ProfileResponse
    private var _ProfilePutResponse = MutableLiveData<Resource<UpdateProfileResponse>>()
    val PutProfileUserResponse: LiveData<Resource<UpdateProfileResponse>> get() = _ProfilePutResponse
    private var _postLoginUserResponse = MutableLiveData<Resource<LoginResponse>>()
    val postLoginUserResponse: LiveData<Resource<LoginResponse>> get() = _postLoginUserResponse

    fun statusLogin(isLogin: Boolean) {
        viewModelScope.launch {
            dataStoreManager.statusLogin(isLogin)
        }
    }

    fun getLoginStatus(): LiveData<Boolean> {
        return dataStoreManager.getLoginStatus().asLiveData()
    }

    fun GetProfileUser(token : String) {
        viewModelScope.launch(Dispatchers.IO) {
            val ProfileGet = userRepository.GetProfileData(token)
            viewModelScope.launch(Dispatchers.Main) {
                _ProfileResponse.postValue(ProfileGet)
            }
        }
    }

    fun PutProfileUser(token : String) {
        viewModelScope.launch(Dispatchers.IO) {
            val ProfilePut = userRepository.PutProfileData(token)
            viewModelScope.launch(Dispatchers.Main) {
                _ProfilePutResponse.postValue(ProfilePut)
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