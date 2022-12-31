package com.example.fpandc13.ui.login

import androidx.lifecycle.*
import com.example.fpandc13.data.repository.UserRepository
import com.example.fpandc13.data.local.preference.UserDataStoreManager
import com.example.fpandc13.data.repository.AuthRepository
import com.example.fpandc13.data.network.models.auth.login.LoginRequestBody
import com.example.fpandc13.data.network.models.auth.login.LoginResponse
import com.example.fpandc13.data.network.models.auth.profile.get.GetUserProfileResponse
import com.example.fpandc13.data.network.models.auth.profile.update.UpdateProfileResponse
import com.example.fpandc13.data.network.models.auth.verify.VerifyRequestBody
import com.example.fpandc13.data.network.models.auth.verify.VerifyResponse
import com.example.fpandc13.wrapper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val dataStoreManager: UserDataStoreManager, private val authRepository: AuthRepository, private val userRepository: UserRepository): ViewModel() {

    private var _postLoginUserResponse = MutableLiveData<Resource<LoginResponse>>()
    private var _postVerifyUserResponse = MutableLiveData<Resource<VerifyResponse>>()
    private var _profilePutResponse = MutableLiveData<Resource<UpdateProfileResponse>>()
    private var _profileResponse = MutableLiveData<Resource<GetUserProfileResponse>>()
    val getProfileUserResponse: LiveData<Resource<GetUserProfileResponse>> get() = _profileResponse
    val putProfileUserResponse: LiveData<Resource<UpdateProfileResponse>> get() = _profilePutResponse
    val postLoginUserResponse: LiveData<Resource<LoginResponse>> get() = _postLoginUserResponse
    val postVerifyUserResponse: LiveData<Resource<VerifyResponse>> get() = _postVerifyUserResponse

    fun statusLogin(isLogin: Boolean) {
        viewModelScope.launch {
            dataStoreManager.statusLogin(isLogin)
        }
    }

    fun getEmail(): LiveData<String> {
        return dataStoreManager.getEmail().asLiveData()
    }

    fun setTokeUser(): LiveData<String> {
        return dataStoreManager.getPassword().asLiveData()
    }

    fun getLoginStatus(): LiveData<Boolean> {
        return dataStoreManager.getLoginStatus().asLiveData()
    }

    fun saveUserToken(isToken: String) {
        viewModelScope.launch {
            userRepository.saveUserToken(isToken)
        }
    }

    fun getDataStoreToken(): LiveData<String> {
        return dataStoreManager.getToken.asLiveData()
    }

    fun postLoginUser(loginRequestBody: LoginRequestBody) {
        viewModelScope.launch(Dispatchers.IO) {
            val loginResponse = authRepository.postLoginUser(loginRequestBody)
            viewModelScope.launch(Dispatchers.Main) {
                _postLoginUserResponse.postValue(loginResponse)
            }
        }
    }

    fun getProfileUser(token : String) {
        viewModelScope.launch(Dispatchers.IO) {
            val ProfileGet = userRepository.getProfileData(token)
            viewModelScope.launch(Dispatchers.Main) {
                _profileResponse.postValue(ProfileGet)
            }
        }
    }

    fun putProfileUser(token : String) {
        viewModelScope.launch(Dispatchers.IO) {
            val ProfilePut = userRepository.putProfileData(token)
            viewModelScope.launch(Dispatchers.Main) {
                _profilePutResponse.postValue(ProfilePut)
            }
        }
    }

    fun postVerifyUser(verifyRequestBody: VerifyRequestBody) {
        viewModelScope.launch(Dispatchers.IO) {
            val VerifyResponse = authRepository.postVerifyUser(verifyRequestBody)
            viewModelScope.launch(Dispatchers.Main) {
               _postVerifyUserResponse.postValue(VerifyResponse)
            }
        }
    }




    fun setUserLogin(isLogin: Boolean) {
        viewModelScope.launch {
            userRepository.setUserLogin(isLogin)
        }
    }

    fun setUserToken(isToken: String) {
        viewModelScope.launch {
            userRepository.setUserToken(isToken)
        }
    }

    fun getUserLoginStatus(): LiveData<Boolean> {
        return userRepository.getUserLoginStatus().asLiveData()
    }

}