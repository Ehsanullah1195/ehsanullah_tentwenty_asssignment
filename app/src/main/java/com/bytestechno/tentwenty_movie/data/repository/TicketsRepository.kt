package com.bytestechno.tentwenty_movie.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.bytestechno.tentwenty_movie.data.models.tickets.Tickets
import com.bytestechno.tentwenty_movie.room.RoomDatabase
import com.bytestechno.tentwenty_movie.room.TicketsDao

class TicketsRepository(private val context: Context) {
    private lateinit var ticketsDao: TicketsDao

    init {
        val database:RoomDatabase = RoomDatabase.getInstance(context)
        ticketsDao = database.ticketDao()
    }

    suspend fun insertTicket(data: Tickets){
        return ticketsDao.insertTicket(data)
    }
}