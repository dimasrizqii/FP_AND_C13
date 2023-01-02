package com.example.fpandc13.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.fpandc13.R
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics
//    private lateinit var firebaseCrashlytics: FirebaseCrashlytics

    override fun onCreate(savedInstanceState: Bundle?) {
        firebaseAnalytics = Firebase.analytics
//        firebaseCrashlytics = Firebase.crashlytics
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        getSupportActionBar()?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}