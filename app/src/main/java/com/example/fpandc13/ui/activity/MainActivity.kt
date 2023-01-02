package com.example.fpandc13.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fpandc13.R
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics
//    private lateinit var firebaseCrashlytics: FirebaseCrashlytics

    override fun onCreate(savedInstanceState: Bundle?) {
        firebaseAnalytics = Firebase.analytics
//        firebaseCrashlytics = Firebase.crashlytics
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()


    }
}