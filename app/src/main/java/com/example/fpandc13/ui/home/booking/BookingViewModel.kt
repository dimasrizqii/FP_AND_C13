package com.example.fpandc13.ui.home.booking

import androidx.lifecycle.*
import com.example.fpandc13.data.local.preference.UserDataStoreManager
import com.example.fpandc13.data.network.models.ticket.list.detail.Ticket
import com.example.fpandc13.data.network.service.ticket.AeroplaneTicketApiInterface
import com.example.fpandc13.data.repository.BookingRepository
import com.example.fpandc13.data.repository.PassengerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BookingViewModel @Inject constructor(
    private val dataStoreManager: UserDataStoreManager,
    private val ApiHelper: AeroplaneTicketApiInterface,
    private val bookingRepository: BookingRepository,
    private val passanggerRepository: PassengerRepository,

): ViewModel() {

    private val _ticket = MutableLiveData<List<Ticket>>()
    val LiveDataTicket: LiveData<List<Ticket>> = _ticket


    fun setNotif(isNotif: Boolean) {
        viewModelScope.launch {
            dataStoreManager.setNotif(isNotif)
        }
    }

    fun getDataStoreAirport(): LiveData<String> {
        return dataStoreManager.bandara.asLiveData()
    }

    fun getDataStoreTanggal(): LiveData<String> {
        return dataStoreManager.date.asLiveData()
    }
    fun getDataStoreAr(): LiveData<String> {
        return dataStoreManager.datang.asLiveData()
    }

    fun getDataStoreDep(): LiveData<String> {
        return dataStoreManager.berangkat.asLiveData()
    }
    fun getDataStorePrice(): LiveData<String> {
        return dataStoreManager.hargatiket.asLiveData()
    }

    fun getDataStoreKelas(): LiveData<String> {
        return dataStoreManager.kelasair.asLiveData()
    }

}