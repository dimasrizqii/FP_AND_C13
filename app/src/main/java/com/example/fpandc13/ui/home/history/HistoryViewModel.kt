package com.example.fpandc13.ui.home.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fpandc13.data.network.models.booking.historyUser.Booking
import com.example.fpandc13.data.network.models.booking.historyUser.BookingResponse
import com.example.fpandc13.data.network.models.ticket.list.detail.Ticket
import com.example.fpandc13.data.network.models.ticket.list.detail.TicketDetailResponse
import com.example.fpandc13.data.network.service.booking.AeroplaneBookingApiInterface
import com.example.fpandc13.data.repository.BookingRepository
import com.example.fpandc13.wrapper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class HistoryViewModel @Inject constructor(private val ApiHelper: AeroplaneBookingApiInterface , private val bookingReposiroty : BookingRepository) : ViewModel()  {

    private val _HistoryBooking: MutableLiveData<BookingResponse> = MutableLiveData()
    val BookingHistory: LiveData<BookingResponse> get() = _HistoryBooking

    private var _HistoryResponse = MutableLiveData<List<Booking>>()
    val HistoryResponse: LiveData<List<Booking>> get() = _HistoryResponse

    private val _booking = MutableLiveData<List<Booking>>()
    val LiveDataBooking: LiveData<List<Booking>> = _booking
    private val _state = MutableLiveData(BookingState())
    val state: LiveData<BookingState> = _state



    fun GetHistoryUser(token : String){
        ApiHelper.getHistoryBooking(token).enqueue(object : Callback<BookingResponse> {
            override fun onResponse(
                call: Call<BookingResponse>,
                response: Response<BookingResponse>
            ) {
                if (response.isSuccessful) {
                    _HistoryResponse.postValue(response.body()!!.bookings as List<Booking>?)
                }
            }
            override fun onFailure(call: Call<BookingResponse>, t: Throwable) {
                Log.e("Error : ", "onFailure: ${t.message}")
            }
        })
    }
}


//    fun GetHistoryUser(token : String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val HistoryUserGet = bookingReposiroty.listPerUserBooking(token)
//            viewModelScope.launch(Dispatchers.Main) {
//                _HistoryBooking.postValue(HistoryUserGet)
//            }
//        }
//    }

//     fun getHistory(token: String){
//        ApiHelper.listPerUserBooking(token).enqueue(object : Callback<BookingResponse> {
//            override fun onResponse(
//                call: Call<BookingResponse>,
//                response: Response<BookingResponse>
//            ) {
//                if (response.isSuccessful) {
//                    _booking.postValue(response.body()!!.bookings as List<Booking>?)
//                }
//            }
//            override fun onFailure(call: Call<BookingResponse>, t: Throwable) {
//                Log.e("Error : ", "onFailure: ${t.message}")
//            }
//        })
//    }




data class BookingState(
    val booking: List<Booking> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
