package com.example.fpandc13.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fpandc13.data.local.datastore.DataStoreManager
import com.example.fpandc13.ui.register.RegisterViewModel

class LoginViewModelFactory(private val dataStore: DataStoreManager): ViewModelProvider.Factory  {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(dataStore) as T
        }
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(dataStore) as T
        }
        throw IllegalArgumentException("Unknown View Model Class ${modelClass.name}")
    }

}