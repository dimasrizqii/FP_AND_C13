package com.example.fpandc13.ui.home.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fpandc13.R

class ProfileFragment : Fragment() {

    private val existUsername = listOf<String>("shawn","peter","raul","mendes")
    private val existName = listOf<String>("shawn","peter","raUL")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    fun validateProfileFragmentInput(
        name: String,
        username: String,
        email: String,
        birthday: String,
        phone: String

    ): Boolean {
        if (name.isEmpty() || username.isEmpty() || email.isEmpty() || birthday.isEmpty() || phone.isEmpty() ){
            return false
        }

        if (username in existUsername) {
            return false
        }

        if (name in existName) {
            return false
        }


        return true
    }


}