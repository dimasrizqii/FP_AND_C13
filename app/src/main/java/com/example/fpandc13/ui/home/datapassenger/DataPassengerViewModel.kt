package com.example.fpandc13.ui.home.datapassenger

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fpandc13.data.network.models.auth.login.LoginRequestBody
import com.example.fpandc13.data.network.models.auth.profile.get.GetUserProfileResponse
import com.example.fpandc13.data.network.models.booking.create.CreateBookingRequestBody
import com.example.fpandc13.data.network.models.booking.create.CreateBookingResponse
import com.example.fpandc13.data.network.models.passenger.create.CreatePassengerRequestBody
import com.example.fpandc13.data.network.models.passenger.create.CreatePassengerResponse
import com.example.fpandc13.data.network.models.passenger.get.PassengerResponse
import com.example.fpandc13.data.network.models.ticket.list.detail.Ticket
import com.example.fpandc13.data.network.models.ticket.list.detail.TicketDetailResponse
import com.example.fpandc13.data.network.service.ticket.AeroplaneTicketApiInterface
import com.example.fpandc13.data.repository.BookingRepository
import com.example.fpandc13.data.repository.PassengerRepository
import com.example.fpandc13.data.repository.TicketRepository
import com.example.fpandc13.wrapper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DataPassengerViewModel @Inject constructor(
    private val ticketsRepository: TicketRepository,
    private val ApiHelper: AeroplaneTicketApiInterface,
    private val transactionsRepository: BookingRepository,
    private val passanggerRepository: PassengerRepository,

): ViewModel() {
    private val _bookRequest = MutableLiveData<Resource<CreateBookingResponse>>()
    val CreateBooking: LiveData<Resource<CreateBookingResponse>> get () = _bookRequest

    private val _PassengerResponse = MutableLiveData<Resource<CreatePassengerResponse>>()
    val CreatePassenger: LiveData<Resource<CreatePassengerResponse>> get() = _PassengerResponse

    private val _ticket = MutableLiveData<Ticket>()
    val LiveDataTicket: LiveData<Ticket> = _ticket

    fun postNewBooking(accessToken: String, bookingRequestBody: CreateBookingRequestBody) {
        viewModelScope.launch(Dispatchers.IO) {
            _bookRequest.postValue(Resource.Loading())
            val response = transactionsRepository.createBooking(bookingRequestBody)
            viewModelScope.launch(Dispatchers.Main) {
                _bookRequest.postValue(response)
            }
        }
    }
    fun postPassengers(createPassengerRequestBody: CreatePassengerRequestBody) {
        viewModelScope.launch(Dispatchers.IO) {
            val Response = passanggerRepository.createPassenger(createPassengerRequestBody)
            viewModelScope.launch(Dispatchers.Main) {
                _PassengerResponse.postValue(Response)
            }
        }
    }

    fun getTicket(){
        ApiHelper.getTicketAll().enqueue(object : Callback<TicketDetailResponse> {
            override fun onResponse(
                call: Call<TicketDetailResponse>,
                response: Response<TicketDetailResponse>
            ) {
                if (response.isSuccessful) {
                    _ticket.postValue(response.body()!!.tickets as Ticket)
                }
            }
            override fun onFailure(call: Call<TicketDetailResponse>, t: Throwable) {
                Log.e("Error : ", "onFailure: ${t.message}")
            }
        })
    }

}