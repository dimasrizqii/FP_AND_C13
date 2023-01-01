package com.example.fpandc13.ui.home.booking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fpandc13.R
import com.example.fpandc13.databinding.FragmentBookingBinding
import com.example.fpandc13.databinding.FragmentDataPassengerBinding


class BookingFragment : Fragment() {

    private var _bindingFrag: FragmentBookingBinding? = null
    private val binding get() = _bindingFrag!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_booking, container, false)
    }


}