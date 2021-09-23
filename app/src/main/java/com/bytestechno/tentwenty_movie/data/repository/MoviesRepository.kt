package com.bytestechno.tentwenty_movie.data.repository

import androidx.lifecycle.MutableLiveData
import com.bytestechno.tentwenty_movie.data.models.movies.Movie
import com.bytestechno.tentwenty_movie.retrofit.APIInterface
import com.bytestechno.tentwenty_movie.utils.Constants.LOADING
import com.bytestechno.tentwenty_movie.utils.Constants.MOVIES
import com.bytestechno.tentwenty_movie.utils.PreferencesManager
import com.bytestechno.tentwenty_movie.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesRepository(private val apiInterface: APIInterface) {

    fun getMovieList(): MutableLiveData<Resource<Movie>> {
        val movies: MutableLiveData<Resource<Movie>> = MutableLiveData()
        movies.postValue(Resource.loading(LOADING, null))
        //ModelPreferencesManager.get<UserData>(LOGIN_PREF_KEY)
        val call: Call<Movie> = apiInterface.movieList("a5c23d52c513d5308b1944a0e862e784")
        call.enqueue(object : Callback<Movie> {
            override fun onResponse(
                call: Call<Movie>,
                response: Response<Movie>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    PreferencesManager.put(data, MOVIES)
                    movies.postValue(Resource.success(200, data))

                } else {
                    when (response.code()) {
                        404 -> {
                            movies.postValue(
                                Resource.error(
                                    404,
                                    "Not Found!",
                                    null
                                )
                            )
                        }
                        401 -> {
                            movies.postValue(
                                Resource.error(
                                    401,
                                    "Unauthorized",
                                    null
                                )
                            )
                        }
                        else -> {
                            movies.postValue(
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

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                call.cancel()
                movies.postValue(
                    Resource.error(
                        400,
                        t.message.toString(),
                        null
                    )
                )
            }

        })

        return movies
    }

}