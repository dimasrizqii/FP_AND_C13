package com.example.fpandc13.ui.home.datapassenger

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fpandc13.data.network.models.booking.create.CreateBookingRequestBody
import com.example.fpandc13.data.network.models.booking.create.CreateBookingResponse
import com.example.fpandc13.data.repository.BookingRepository
import com.example.fpandc13.data.repository.TicketRepository
import com.example.fpandc13.wrapper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataPassengerViewModel @Inject constructor(
    private val ticketsRepository: TicketRepository,
    private val transactionsRepository: BookingRepository
): ViewModel() {
    private val _bookResponse = MutableLiveData<Resource<CreateBookingResponse>>()
    val bookingResponse: LiveData<Resource<CreateBookingResponse>> = _bookResponse

    fun postNewBooking(accessToken: String, bookingRequestBody: CreateBookingRequestBody) {
        viewModelScope.launch(Dispatchers.IO) {
            _bookResponse.postValue(Resource.Loading())
            val response = transactionsRepository.createBooking(bookingRequestBody)
            viewModelScope.launch(Dispatchers.Main) {
                _bookResponse.postValue(response)
            }
        }
    }

}