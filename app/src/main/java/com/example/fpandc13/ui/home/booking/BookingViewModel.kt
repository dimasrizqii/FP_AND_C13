package com.example.fpandc13.ui.home.booking

import androidx.lifecycle.ViewModel
import com.example.fpandc13.data.local.preference.UserDataStoreManager
import com.example.fpandc13.data.network.service.ticket.AeroplaneTicketApiInterface
import com.example.fpandc13.data.repository.BookingRepository
import com.example.fpandc13.data.repository.PassengerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class BookingViewModel @Inject constructor(
    private val dataStoreManager: UserDataStoreManager,
    private val ApiHelper: AeroplaneTicketApiInterface,
    private val bookingRepository: BookingRepository,
    private val passanggerRepository: PassengerRepository,

): ViewModel() {

}