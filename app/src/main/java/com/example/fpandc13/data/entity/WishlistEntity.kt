package com.example.fpandc13.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class WishlistEntity(
    @field:ColumnInfo(name = "id")
    @field:PrimaryKey
    var id: Int,

    @field:ColumnInfo(name = "airportname")
    var airportName: String,

    @field:ColumnInfo(name = "logo_path")
    val appLogoPath: String,

    @field:ColumnInfo(name = "price")
    var price: String,

    @field:ColumnInfo(name = "departure")
    var departure: String,

    @field:ColumnInfo(name = "arrival")
    var arrival: String,

    @field:ColumnInfo(name = "departure_time")
    var departureTime: String,

    @field:ColumnInfo(name = "arrival_time")
    var arrivalTime: String,

    @field:ColumnInfo(name = "kelas")
    var kelas: String,

    ): Parcelable
