package com.example.fpandc13.ui.home.wishlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.fpandc13.data.entity.WishlistEntity
import com.example.fpandc13.room.WishlistDB
import com.example.fpandc13.room.WishlistDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class WishlistViewModel(application: Application) : AndroidViewModel(application) {

    private var wishlistDao: WishlistDao?
    private var wishlistDB: WishlistDB?

    init {
        wishlistDB = WishlistDB.getDatabase(application)
        wishlistDao = wishlistDB?.WishlistDao()
    }

    fun addToWishlist(id: Int,
                      airportName: String,
                      appLogoPath: String,
                      price: String,
                      departure: String,
                      arrival: String,
                      departureTime: String,
                      arrivalTime: String,
                      kelas: String,
                       ) {
        CoroutineScope(Dispatchers.IO).launch {
            var ticket = WishlistEntity(id, airportName, appLogoPath, price, departure, arrival, departureTime, arrivalTime, kelas)
            wishlistDao?.insertWishlist(ticket)
        }
    }

    suspend fun checkTicket(id: Int) = wishlistDao?.checkTicket(id)

    fun removeFromWishlist(id: Int,
                           airportName: String,
                           appLogoPath: String,
                           price: String,
                           departure: String,
                           arrival: String,
                           departureTime: String,
                           arrivalTime: String,
                           kelas: String,
                           date: String,
                           ) {
        CoroutineScope(Dispatchers.IO).launch {
            var ticket = WishlistEntity(id, airportName, appLogoPath, price, departure, arrival, departureTime, arrivalTime, kelas)
            wishlistDao?.deleteWishlist(ticket)
        }
    }

    fun getWishlistTicket(): LiveData<List<WishlistEntity>>? {
        return wishlistDao?.getDataWishlist()
    }
}