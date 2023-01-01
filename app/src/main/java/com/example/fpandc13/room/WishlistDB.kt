package com.example.fpandc13.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fpandc13.data.entity.WishlistEntity


@Database(entities = [WishlistEntity::class], version = 1,exportSchema = false)
abstract class WishlistDB : RoomDatabase() {
    abstract fun WishlistDao(): WishlistDao

    companion object {
        @Volatile
        private var INSTANCE: WishlistDB? = null
        @JvmStatic
        fun getDatabase(context: Context): WishlistDB {
            if (INSTANCE == null) {
                synchronized(WishlistDB::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        WishlistDB::class.java, "wishlist_db")
                        .build()
                }
            }
            return INSTANCE as WishlistDB
        }
    }
}