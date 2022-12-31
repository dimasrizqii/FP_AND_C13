package com.example.fpandc13.ui.home.ticket

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fpandc13.data.local.preference.UserDataStoreManager
import com.example.fpandc13.data.network.models.ticket.list.detail.Ticket
import com.example.fpandc13.data.network.models.ticket.list.detail.TicketDetailResponse
import com.example.fpandc13.data.network.service.ticket.AeroplaneTicketApiInterface
import com.example.fpandc13.data.repository.AuthRepository
import com.example.fpandc13.data.repository.TicketRepository
import com.example.fpandc13.data.repository.UserRepository
import com.example.fpandc13.databinding.ItemTicketListBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class TicketViewModel @Inject constructor( private val ApiHelper: AeroplaneTicketApiInterface, private val ticketsRepository: TicketRepository): ViewModel() {

    init {
        getTicket()
    }

    private val _ticket = MutableLiveData<List<Ticket>>()
    val LiveDataTicket: LiveData<List<Ticket>> = _ticket
    private val _userData = MutableLiveData(TicketState())
    val userData: LiveData<TicketState> = _userData
    private val _ticketResponse = MutableLiveData<TicketDetailResponse>()
    val DataTicket: LiveData<TicketDetailResponse> = _ticketResponse
    private val _state = MutableLiveData(TicketState())
    val state: LiveData<TicketState> = _state


    fun getTicket(){
        ApiHelper.getTicketAll().enqueue(object : Callback<TicketDetailResponse> {
            override fun onResponse(
                call: Call<TicketDetailResponse>,
                response: Response<TicketDetailResponse>
            ) {
                if (response.isSuccessful) {
                    _ticket.postValue(response.body()!!.tickets as List<Ticket>?)
                }
            }
            override fun onFailure(call: Call<TicketDetailResponse>, t: Throwable) {
                Log.e("Error : ", "onFailure: ${t.message}")
            }
        })
    }
}


data class TicketState(
    val ticket: List<Ticket> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
