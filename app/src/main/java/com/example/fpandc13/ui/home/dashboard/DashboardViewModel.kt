package com.example.fpandc13.ui.home.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fpandc13.data.local.preference.UserDataStoreManager
import com.example.fpandc13.data.repository.AuthRepository
import com.example.fpandc13.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(private val dataStoreManager: UserDataStoreManager, private val authRepository: AuthRepository, private val userRepository: UserRepository): ViewModel() {

    fun statusNotif(isNotif: Boolean) {
        viewModelScope.launch {
            dataStoreManager.statusNotif(isNotif)
        }
    }

    fun setNotif(isNotif: Boolean) {
        viewModelScope.launch {
            dataStoreManager.setNotif(isNotif)
        }
    }

    fun getNotif(): LiveData<Boolean> {
        return dataStoreManager.getNotifStatus().asLiveData()
    }




}