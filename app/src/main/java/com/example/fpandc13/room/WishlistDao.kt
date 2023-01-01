package com.example.fpandc13.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.fpandc13.data.entity.WishlistEntity


@Dao
interface WishlistDao {
    @Query("SELECT * FROM WishlistEntity ORDER BY id DESC")
    fun getDataWishlist() : LiveData<List<WishlistEntity>>

    @Insert
    fun insertWishlist(ticket: WishlistEntity)

    @Delete
    fun deleteWishlist(ticket: WishlistEntity)

    @Query("SELECT * FROM WishlistEntity WHERE id = :id")
    fun checkTicket(id: Int) : Int
}