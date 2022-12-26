package com.example.fpandc13.data.repository

import android.util.JsonToken
import com.example.fpandc13.data.local.datasource.UserLocalDataSource
import com.example.fpandc13.data.network.datasource.AuthRemoteDataSource
import com.example.fpandc13.data.network.models.auth.profile.get.GetProfileResponse
import com.example.fpandc13.data.network.models.auth.profile.update.UpdateProfileResponse
import com.example.fpandc13.data.network.models.auth.register.RegisterRequestBody
import com.example.fpandc13.data.network.models.auth.register.RegisterResponse
import com.example.fpandc13.wrapper.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface UserRepository {
    suspend fun setUserLogin(isLogin: Boolean)
    suspend fun getProfileData(token: String): Resource<GetProfileResponse>
    suspend fun putProfileData(token: String): Resource<UpdateProfileResponse>
    fun getUserLoginStatus(): Flow<Boolean>
}

class UserRepositoryImpl @Inject constructor(private val userLocalDataSource: UserLocalDataSource, private val dataSource: AuthRemoteDataSource) : UserRepository {
    override suspend fun setUserLogin(isLogin: Boolean) {
        return userLocalDataSource.setUserLogin(isLogin)
    }

    override suspend fun getProfileData(token: String): Resource<GetProfileResponse>{
        return proceed {
            dataSource.getProfile(token)
        }
    }

    override suspend fun putProfileData(token: String): Resource<UpdateProfileResponse>{
        return proceed {
            dataSource.putProfile(token)
        }
    }

    override fun getUserLoginStatus(): Flow<Boolean> {
        return userLocalDataSource.getUserLoginStatus()
    }

    private suspend fun <T> proceed(coroutines: suspend () -> T): Resource<T> {
        return try {
            Resource.Success(coroutines.invoke())
        } catch (e: Exception) {
            Resource.Error(e, e.message)
        }
    }

}