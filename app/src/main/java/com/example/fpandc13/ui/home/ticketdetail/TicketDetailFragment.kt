package com.example.fpandc13.ui.home.ticketdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fpandc13.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TicketDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket_detail, container, false)
    }

}