package com.example.fpandc13.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fpandc13.data.local.datastore.DataStoreManager
import kotlinx.coroutines.launch

class RegisterViewModel(private val dataStoreManager: DataStoreManager): ViewModel()  {

    fun saveAccount(email: String, password: String, phoneNumber: String) {
        viewModelScope.launch {
            dataStoreManager.setEmail(email)
            dataStoreManager.setPassword(password)
            dataStoreManager.setPhoneNumber(phoneNumber)
        }
    }

}