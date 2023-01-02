package com.example.fpandc13.ui.home.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fpandc13.data.network.models.ticket.list.detail.Airport
import com.example.fpandc13.data.network.models.ticket.list.detail.Ticket
import com.example.fpandc13.data.network.service.ticket.AeroplaneTicketApiInterface
import com.example.fpandc13.data.repository.TicketRepository
import com.example.fpandc13.ui.home.ticket.TicketState
import com.example.fpandc13.wrapper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(private val ApiHelper: AeroplaneTicketApiInterface, private val ticketsRepository: TicketRepository): ViewModel() {

    private val _ticketsResult = MutableLiveData<Resource<List<Ticket>>>()
    val ticketsResult: LiveData<Resource<List<Ticket>>> get() = _ticketsResult

    private val _ticket = MutableLiveData<List<Ticket>>()
    val LiveDataTicket: LiveData<List<Ticket>> = _ticket

    private val _state = MutableLiveData(TicketState())
    val state: LiveData<TicketState> = _state


    fun getTickets(
        accessToken : String,
        airport: String,
        airportloc : String,
        dep : String,
        ar: String,
        price : Int,
        kelas : String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _ticketsResult.postValue(Resource.Loading())

            val ticketsItem = ticketsRepository.getSearchTicket(
                accessToken ,
                airport,
                airportloc,
                dep,
                ar,
                price,
                kelas
            ).payload?.ticket

            Log.d(
                "testing",
                ticketsRepository.getSearchTicket(
                    accessToken ,
                    airport,
                    airportloc,
                    dep,
                    ar,
                    price,
                    kelas
                ).payload?.ticket.toString()
            )
            viewModelScope.launch(Dispatchers.Main) {
                val data = if (ticketsItem.isNullOrEmpty()) Resource.Empty() else Resource.Success(
                    ticketsItem
                )
                _ticketsResult.postValue(data)
            }
        }
    }
}