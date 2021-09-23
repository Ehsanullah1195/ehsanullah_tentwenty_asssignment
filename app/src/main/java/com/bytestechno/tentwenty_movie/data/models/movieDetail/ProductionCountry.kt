package com.bytestechno.tentwenty_movie.data.models.movieDetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductionCountry(

        @SerializedName("iso_3166_1")
        @Expose
        val iso31661: String,

        @SerializedName("name")
        @Expose
        val name: String,
)
