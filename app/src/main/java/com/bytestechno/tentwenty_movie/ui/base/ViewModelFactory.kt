package com.bytestechno.tentwenty_movie.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bytestechno.tentwenty_movie.data.repository.MovieDetailRepository
import com.bytestechno.tentwenty_movie.data.repository.MoviesRepository
import com.bytestechno.tentwenty_movie.retrofit.APIInterface
import com.bytestechno.tentwenty_movie.ui.activities.dashboard.MainVM
import com.bytestechno.tentwenty_movie.ui.activities.movieDetail.MovieDetailVM

class ViewModelFactory(private val apiHelper: APIInterface) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainVM::class.java)) {
            return MainVM(MoviesRepository(apiHelper)) as T
        }else if (modelClass.isAssignableFrom(MovieDetailVM::class.java)) {
            return MovieDetailVM(MovieDetailRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}