package com.bytestechno.tentwenty_movie.ui.activities.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bytestechno.tentwenty_movie.data.models.movies.Movie
import com.bytestechno.tentwenty_movie.data.repository.MoviesRepository
import com.bytestechno.tentwenty_movie.utils.Resource

class MainVM(private val repository: MoviesRepository) : ViewModel() {
    private lateinit var allMovies: LiveData<Resource<Movie>>

    init {
        movies()
    }
    private fun movies(){
        allMovies = repository.getMovieList()
    }

    fun getMovies(): LiveData<Resource<Movie>> {
        return allMovies
    }
}