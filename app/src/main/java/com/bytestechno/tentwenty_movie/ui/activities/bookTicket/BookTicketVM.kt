package com.bytestechno.tentwenty_movie.ui.activities.bookTicket

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bytestechno.tentwenty_movie.data.models.tickets.Tickets
import com.bytestechno.tentwenty_movie.data.repository.TicketsRepository
import kotlinx.coroutines.runBlocking

class BookTicketVM(application: Application) : AndroidViewModel(application)  {
    private val repository:TicketsRepository = TicketsRepository(application.applicationContext)
    var ticket:MutableLiveData<Long?> = MutableLiveData()

    fun insertTicket(data: Tickets) = runBlocking {
            repository.insertTicket(data)
    }
}