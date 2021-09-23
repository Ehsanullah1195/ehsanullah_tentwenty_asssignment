package com.bytestechno.tentwenty_movie.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bytestechno.tentwenty_movie.data.models.movieDetail.MovieDetail
import com.bytestechno.tentwenty_movie.data.models.movies.Movie
import com.bytestechno.tentwenty_movie.data.models.slider.SliderItem
import com.bytestechno.tentwenty_movie.retrofit.APIInterface
import com.bytestechno.tentwenty_movie.utils.Constants
import com.bytestechno.tentwenty_movie.utils.Constants.DETAILS
import com.bytestechno.tentwenty_movie.utils.Constants.LOADING
import com.bytestechno.tentwenty_movie.utils.PreferencesManager
import com.bytestechno.tentwenty_movie.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailRepository(private val apiInterface: APIInterface) {

    fun getMovieDetail(seriesId:Int, APIKey:String): MutableLiveData<Resource<MovieDetail>> {
        val detail: MutableLiveData<Resource<MovieDetail>> = MutableLiveData()
        detail.postValue(Resource.loading(LOADING, null))
        val call: Call<MovieDetail> = apiInterface.movieDetail(seriesId, APIKey)
        call.enqueue(object : Callback<MovieDetail> {
            override fun onResponse(
                call: Call<MovieDetail>,
                response: Response<MovieDetail>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data!!.commaSeparateGenre = data.genres.joinToString { it -> it.name }
                    PreferencesManager.put(data, DETAILS)
                    detail.postValue(Resource.success(200, data))

                } else {
                    when (response.code()) {
                        404 -> {
                            detail.postValue(
                                Resource.error(
                                    404,
                                    "Not Found!",
                                    null
                                )
                            )
                        }
                        401 -> {
                            detail.postValue(
                                Resource.error(
                                    401,
                                    "Unauthorized",
                                    null
                                )
                            )
                        }
                        else -> {
                            detail.postValue(
                                Resource.error(
                                    501,
                                    "Unknown error",
                                    null
                                )
                            )
                        }
                    }
                }
            }

            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                call.cancel()
                detail.postValue(
                    Resource.error(
                        400,
                        t.message.toString(),
                        null
                    )
                )
            }

        })

        return detail
    }

    fun sliderPoster(id: Int, apiKey: String): LiveData<Resource<SliderItem>> {
        val posters: MutableLiveData<Resource<SliderItem>> = MutableLiveData()
        posters.postValue(Resource.loading(LOADING, null))
        val call: Call<SliderItem> = apiInterface.posters(id, apiKey)
        call.enqueue(object : Callback<SliderItem> {
            override fun onResponse(
                call: Call<SliderItem>,
                response: Response<SliderItem>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    posters.postValue(Resource.success(200, data))

                } else {
                    when (response.code()) {
                        404 -> {
                            posters.postValue(
                                Resource.error(
                                    404,
                                    "Not Found!",
                                    null
                                )
                            )
                        }
                        401 -> {
                            posters.postValue(
                                Resource.error(
                                    401,
                                    "Unauthorized",
                                    null
                                )
                            )
                        }
                        else -> {
                            posters.postValue(
                                Resource.error(
                                    501,
                                    "Unknown error",
                                    null
                                )
                            )
                        }
                    }
                }
            }

            override fun onFailure(call: Call<SliderItem>, t: Throwable) {
                call.cancel()
                posters.postValue(
                    Resource.error(
                        400,
                        t.message.toString(),
                        null
                    )
                )
            }

        })

        return posters
    }

}