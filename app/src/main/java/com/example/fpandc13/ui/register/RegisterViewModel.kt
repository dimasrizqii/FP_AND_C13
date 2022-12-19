package com.example.fpandc13.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binar.gosky.data.repository.UserRepository
import com.example.authaeroplane.data.local.preference.UserDataStoreManager
import com.example.authaeroplane.data.repository.AuthRepository
import com.example.fpandc13.models.auth.register.RegisterRequestBody
import com.example.fpandc13.models.auth.register.RegisterResponse
import com.example.fpandc13.wrapper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: AuthRepository, private val userRepository: UserRepository, private val dataStoreManager: UserDataStoreManager) : ViewModel() {

    private var _postRegisterUserResponse = MutableLiveData<Resource<RegisterResponse>>()
    val postRegisterUserResponse: LiveData<Resource<RegisterResponse>> get() = _postRegisterUserResponse


    fun postRegisterUser(registerRequestBody: RegisterRequestBody) {
        viewModelScope.launch(Dispatchers.IO) {
            val registerResponse = repository.postRegisterUser(registerRequestBody)
            viewModelScope.launch(Dispatchers.Main) {
                _postRegisterUserResponse.postValue(registerResponse)
            }
        }
    }

        fun saveAccount() {
        viewModelScope.launch {
            dataStoreManager.getEmail().toString()
            dataStoreManager.getPassword().toString()

        }
    }

}




