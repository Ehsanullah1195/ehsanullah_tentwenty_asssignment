package com.bytestechno.tentwenty_movie.data.models.tickets

import android.content.res.TypedArray
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ticket_detail")
data class Tickets(
    @PrimaryKey
    var movieId: Int,

    var movieName: String,

    var releaseDate: String,

    var locationId: Int,

    var cinemaId: Int,

    var seatNum: Int,

    var ticketNum: Int,
)
