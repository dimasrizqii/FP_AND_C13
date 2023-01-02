package com.example.fpandc13.ui.home.payment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.fpandc13.R
import com.example.fpandc13.databinding.FragmentDataPassengerBinding
import com.example.fpandc13.databinding.FragmentPaymentBinding
import com.example.fpandc13.ui.activity.Home.HomeActivity


class PaymentFragment : Fragment() {

    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!


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
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBook.setOnClickListener(){
            navigateHome()
            Toast.makeText(requireContext(), "Tagihan Telah Diabayar", Toast.LENGTH_LONG).show()
        }

    }

    private fun navigateHome(){
        activity?.let { it ->
            val intent = Intent(it, HomeActivity::class.java)
            it.startActivity(intent)}
    }
}