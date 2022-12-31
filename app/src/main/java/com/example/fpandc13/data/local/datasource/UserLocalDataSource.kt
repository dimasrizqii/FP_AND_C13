package com.example.fpandc13.data.local.datasource

import com.example.fpandc13.data.local.preference.UserDataStoreManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface UserLocalDataSource {
    suspend fun setUserLogin(isLogin: Boolean)
    suspend fun setUserToken(isToken: String)
    suspend fun saveUserToken(isToken: String)

    fun getUserLoginStatus(): Flow<Boolean>
}

class UserLocalDataSourceImpl @Inject constructor(
    private val userDataStore: UserDataStoreManager
) : UserLocalDataSource {

    override suspend fun setUserLogin(isLogin: Boolean) {
        userDataStore.setUserLogin(isLogin)
    }

    override suspend fun setUserToken(isToken: String) {
        userDataStore.setToken(isToken)
    }

    override suspend fun saveUserToken(isToken: String) {
        userDataStore.GetToken(isToken)
    }

    override fun getUserLoginStatus(): Flow<Boolean> {
        return userDataStore.getUserLoginStatus()
    }
}