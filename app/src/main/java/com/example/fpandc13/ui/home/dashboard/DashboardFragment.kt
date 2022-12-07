package com.example.fpandc13.ui.home.dashboard


import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup

import android.os.Bundle
import android.view.View
import com.example.fpandc13.R

import com.example.fpandc13.databinding.FragmentDashboardBinding


class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }
}

