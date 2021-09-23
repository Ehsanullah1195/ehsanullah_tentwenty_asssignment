package com.bytestechno.tentwenty_movie.data.models.movies

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Movie(

        @SerializedName("dates")
        @Expose
        val dates:Dates,

        @SerializedName("page")
        @Expose
        val page:Int,

        @SerializedName("results")
        @Expose
        val results:List<MovieData>,

        @SerializedName("total_pages")
        @Expose
        val totalPages:Int,

        @SerializedName("total_results")
        @Expose
        val totalResults:Int,

)
