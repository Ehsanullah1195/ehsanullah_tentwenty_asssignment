package com.bytestechno.tentwenty_movie.ui.activities.movieDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bytestechno.tentwenty_movie.data.models.movieDetail.MovieDetail
import com.bytestechno.tentwenty_movie.data.models.slider.SliderItem
import com.bytestechno.tentwenty_movie.data.repository.MovieDetailRepository
import com.bytestechno.tentwenty_movie.utils.Resource

class MovieDetailVM(private val repository: MovieDetailRepository) : ViewModel() {
    private var movieDetail: LiveData<Resource<MovieDetail>> = MutableLiveData()
    private var sliderPoster: LiveData<Resource<SliderItem>> = MutableLiveData()

    public fun movieDetail(seriesId: Int, APIKey: String) {
        movieDetail = repository.getMovieDetail(seriesId, APIKey)
    }

    public fun getMovieDetail(): LiveData<Resource<MovieDetail>> {
        return movieDetail
    }

    fun sliderPoster(id: Int, api_key: String) {
        sliderPoster = repository.sliderPoster(id, api_key)
    }

    fun getPosters(): LiveData<Resource<SliderItem>> {
        return sliderPoster
    }
}
