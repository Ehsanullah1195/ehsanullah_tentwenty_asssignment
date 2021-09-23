package com.bytestechno.tentwenty_movie.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bytestechno.tentwenty_movie.data.models.tickets.Tickets

@Dao
interface TicketsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTicket(data: Tickets)


    @Query("SELECT * FROM ticket_detail WHERE movieId = :id")
    fun getTicketDetail(id: Int): LiveData<Tickets>

}