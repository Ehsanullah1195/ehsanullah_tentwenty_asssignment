package com.bytestechno.tentwenty_movie.data.models.movieDetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SpokenLanguage(
        @SerializedName("english_name")
        @Expose
        val englishName:String,

        @SerializedName("iso_639_1")
        @Expose
        val iso6391:String,

        @SerializedName("name")
        @Expose
        val name:String,
)