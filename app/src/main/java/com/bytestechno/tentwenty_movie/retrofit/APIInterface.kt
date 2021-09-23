package com.bytestechno.tentwenty_movie.retrofit

import com.bytestechno.tentwenty_movie.data.models.movieDetail.MovieDetail
import com.bytestechno.tentwenty_movie.data.models.movies.Movie
import com.bytestechno.tentwenty_movie.data.models.slider.SliderItem
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {


    @GET("3/movie/upcoming")
    fun movieList(
            @Query("api_key") api_key:String,
    ): Call<Movie>

    @GET("3/movie/{movie_id}")
    fun movieDetail(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") apiKey:String
    ): Call<MovieDetail>

    @GET("3/movie/{movie_id}/images")
    fun posters(
        @Path("movie_id")id: Int,
        @Query("api_key")apiKey: String
    ): Call<SliderItem>

}