package com.example.fpandc13.data.local.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDataStoreManager @Inject constructor(@ApplicationContext private val context: Context) {

    suspend fun setUserLogin(isLogin: Boolean) {
        context.userDataStore.edit { preferences ->
            preferences[LOGIN_STATUS_KEY] = isLogin
        }
    }

    fun getUserLoginStatus(): Flow<Boolean> {
        return context.userDataStore.data.map { preferences ->
            preferences[LOGIN_STATUS_KEY] ?: false
        }
    }

    fun getNotifStatus(): Flow<Boolean> {
        return context.userDataStore.data.map { preferences ->
            preferences[ID_NOTIF] ?: false
        }
    }

    suspend fun setNotif(isNotif: Boolean) {
        context.userDataStore.edit { preferences ->
            preferences[ID_NOTIF] = isNotif
        }
    }

    suspend fun statusNotif(isNotif: Boolean) {
        context.userDataStore.edit {
            it[ID_NOTIF] = isNotif
        }
    }

    suspend fun statusLogin(isLogin: Boolean) {
        context.userDataStore.edit {
            it[LOGIN_STATUS] = isLogin
        }
    }

    fun getEmail(): Flow<String> {
        return context.userDataStore.data.map {
            it[EMAIL] ?: ""
        }
    }

    suspend fun GetToken(token: String) {
        context.userDataStore.edit {
            it[TOKEN] = token
        }
    }

    suspend fun removeToken() {
        context.userDataStore.edit {
            it.remove(TOKEN)
        }
    }

    fun getAirport(): Flow<String> {
        return context.userDataStore.data.map {
            it[AIRPORT] ?: ""
        }
    }
    fun getTanggal(): Flow<String> {
        return context.userDataStore.data.map {
            it[TANGGAL] ?: ""
        }
    }
    fun getArriv(): Flow<String> {
        return context.userDataStore.data.map {
            it[ARRIVAL] ?: ""
        }
    }
    fun getDep(): Flow<String> {
        return context.userDataStore.data.map {
            it[DEPARTURE] ?: ""
        }
    }
    fun getHarga(): Flow<String> {
        return context.userDataStore.data.map {
            it[PRICE] ?: ""
        }
    }
    fun getKelas(): Flow<String> {
        return context.userDataStore.data.map {
            it[KELAS] ?: ""
        }
    }

    val bandara : Flow<String> = context.userDataStore.data.map {
        it[AIRPORT] ?: ""
    }
    val date : Flow<String> = context.userDataStore.data.map {
        it[TANGGAL] ?: ""
    }
    val datang : Flow<String> = context.userDataStore.data.map {
        it[ARRIVAL] ?: ""
    }
    val berangkat : Flow<String> = context.userDataStore.data.map {
        it[DEPARTURE] ?: ""
    }
    val hargatiket : Flow<String> = context.userDataStore.data.map {
        it[PRICE] ?: ""
    }
    val kelasair : Flow<String> = context.userDataStore.data.map {
        it[KELAS] ?: ""
    }

    suspend fun setairport(isAirport: String) {
        context.userDataStore.edit { preferences ->
            preferences[AIRPORT] = isAirport
        }
    }

    suspend fun setTanggal(isTanggal: String) {
        context.userDataStore.edit { preferences ->
            preferences[TANGGAL] = isTanggal
        }
    }

    suspend fun setArrival(isArrival: String) {
        context.userDataStore.edit { preferences ->
            preferences[ARRIVAL] = isArrival
        }
    }

    suspend fun setDeparture(isDeparture: String) {
        context.userDataStore.edit { preferences ->
            preferences[DEPARTURE] = isDeparture
        }
    }

    suspend fun setHarga(isHarga: String) {
        context.userDataStore.edit { preferences ->
            preferences[PRICE] = isHarga
        }
    }

    suspend fun setKelas(isKelas: String) {
        context.userDataStore.edit { preferences ->
            preferences[KELAS] = isKelas

        }
    }



    fun getPassword(): Flow<String> {
        return context.userDataStore.data.map {
            it[PASSWORD] ?: ""
        }
    }

    fun getToken(): Flow<String> {
        return context.userDataStore.data.map {
            it[TOKEN] ?: ""
        }
    }

    suspend fun setToken(isToken: String) {
        context.userDataStore.edit { preferences ->
            preferences[TOKEN] = isToken
        }
    }

    val getToken: Flow<String> = context.userDataStore.data.map {
        it[TOKEN] ?: ""
    }



    suspend fun setTicket(isTicket: String) {
        context.userDataStore.edit { preferences ->
            preferences[ID_TICKET] = isTicket
        }
    }

    val getTicket: Flow<String> = context.userDataStore.data.map {
        it[ID_TICKET] ?: ""
    }



    suspend fun removeTicket() {
        context.userDataStore.edit {
            it.remove(ID_TICKET)
        }
    }


    suspend fun setPassenger(isPassenger: String) {
        context.userDataStore.edit { preferences ->
            preferences[ID_PASSENGER] = isPassenger
        }
    }

    val getPassenger: Flow<String> = context.userDataStore.data.map {
        it[ID_PASSENGER] ?: ""
    }

    suspend fun removePassenger() {
        context.userDataStore.edit {
            it.remove(ID_PASSENGER)
        }
    }

    suspend fun setUser(isUser: String) {
        context.userDataStore.edit { preferences ->
            preferences[ID_USER] = isUser
        }
    }

    val getUser: Flow<String> = context.userDataStore.data.map {
        it[ID_USER] ?: ""
    }
    suspend fun removeUser() {
        context.userDataStore.edit {
            it.remove(ID_USER)
        }
    }

    fun getLoginStatus(): Flow<Boolean> {
        return context.userDataStore.data.map {
            it[LOGIN_STATUS] ?: false
        }
    }

    companion object {
        private const val DATA_STORE_NAME = "user_preferences"
        private const val DATASTORE_NAME = "database"

        private val AIRPORT = stringPreferencesKey("airport")
        private val TANGGAL = stringPreferencesKey("tanggal")
        private val ARRIVAL = stringPreferencesKey("arrival")
        private val DEPARTURE = stringPreferencesKey("departure")
        private val PRICE = stringPreferencesKey("harga")
        private val KELAS = stringPreferencesKey("kelas")

        private val USERNAME = stringPreferencesKey("username_key")
        private val TOKEN = stringPreferencesKey("token")
        private val ID_USER = stringPreferencesKey("id_user")
        private val ID_TICKET = stringPreferencesKey("id_ticket")
        private val ID_NOTIF = booleanPreferencesKey("id_boolean")
        private val ID_PASSENGER = stringPreferencesKey("id_passenger")
        private val PASSWORD = stringPreferencesKey("password_key")
        private val EMAIL = stringPreferencesKey("email_key")
        private val ADDRESS = stringPreferencesKey("address_key")
        private val FULLNAME = stringPreferencesKey("fullname_key")
        private val PHONE_NUMBER = stringPreferencesKey("phone_number_key")
        private val LOGIN_STATUS = booleanPreferencesKey("login_status_key")

        private val LOGIN_STATUS_KEY = booleanPreferencesKey("login_status_key")

        val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)
    }
}
