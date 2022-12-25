package com.example.fpandc13.ui.activity.Home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fpandc13.data.local.preference.UserDataStoreManager
import com.example.fpandc13.data.network.models.auth.profile.get.GetProfileResponse
import com.example.fpandc13.data.network.models.auth.profile.update.UpdateProfileResponse
import com.example.fpandc13.data.repository.UserRepository
import com.example.fpandc13.wrapper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val dataStoreManager: UserDataStoreManager, private val authRepository: UserRepository) : ViewModel() {
    private var _ProfileResponse = MutableLiveData<Resource<GetProfileResponse>>()
    val GetProfileUserResponse: LiveData<Resource<GetProfileResponse>> get() = _ProfileResponse
    private var _ProfilePutResponse = MutableLiveData<Resource<UpdateProfileResponse>>()
    val PutProfileUserResponse: LiveData<Resource<UpdateProfileResponse>> get() = _ProfilePutResponse



    fun GetProfileUser(token : String) {
        viewModelScope.launch(Dispatchers.IO) {
            val ProfileGet = authRepository.GetProfileData(token)
            viewModelScope.launch(Dispatchers.Main) {
                _ProfileResponse.postValue(ProfileGet)
            }
        }
    }

    fun PutProfileUser(token : String) {
        viewModelScope.launch(Dispatchers.IO) {
            val ProfilePut = authRepository.PutProfileData(token)
            viewModelScope.launch(Dispatchers.Main) {
                _ProfilePutResponse.postValue(ProfilePut)
            }
        }
    }
}
