package com.example.fpandc13.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {

    suspend fun setUsername(username: String) {
        context.accountDataStore.edit {
            it[USERNAME] = username
        }
    }

    suspend fun setPassword(password: String) {
        context.accountDataStore.edit {
            it[PASSWORD] = password
        }
    }

    suspend fun setEmail(email: String) {
        context.accountDataStore.edit {
            it[EMAIL] = email
        }
    }

    suspend fun setPhoneNumber(phoneNumber: String) {
        context.accountDataStore.edit {
            it[PHONE_NUMBER] = phoneNumber
        }
    }

    suspend fun setFullname(fullname: String) {
        context.accountDataStore.edit {
            it[FULLNAME] = fullname
        }
    }

    suspend fun setAddress(address: String){
        context.accountDataStore.edit {
            it[ADDRESS] = address
        }
    }


    suspend fun statusLogin(isLogin: Boolean) {
        context.accountDataStore.edit {
            it[LOGIN_STATUS] = isLogin
        }
    }

    fun getEmail(): Flow<String> {
        return context.accountDataStore.data.map {
            it[EMAIL] ?: ""
        }
    }

    fun getUsername(): Flow<String> {
        return context.accountDataStore.data.map {
            it[USERNAME] ?: ""
        }
    }

    fun getPassword(): Flow<String> {
        return context.accountDataStore.data.map {
            it[PASSWORD] ?: ""
        }
    }

    fun getLoginStatus(): Flow<Boolean> {
        return context.accountDataStore.data.map {
            it[LOGIN_STATUS] ?: false
        }
    }

    companion object {
        private const val DATASTORE_NAME = "database"

        private val USERNAME = stringPreferencesKey("username_key")
        private val PASSWORD = stringPreferencesKey("password_key")
        private val EMAIL = stringPreferencesKey("email_key")
        private val ADDRESS = stringPreferencesKey("address_key")
        private val FULLNAME = stringPreferencesKey("fullname_key")
        private val PHONE_NUMBER = stringPreferencesKey("phone_number_key")
        private val LOGIN_STATUS = booleanPreferencesKey("login_status_key")

        private val Context.accountDataStore by preferencesDataStore(
            name = DATASTORE_NAME
        )
    }

}