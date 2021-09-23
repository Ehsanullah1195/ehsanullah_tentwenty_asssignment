package com.bytestechno.tentwenty_movie.data.models.slider

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Poster(
    @SerializedName("aspect_ratio")
    @Expose
    val aspectRatio: Double,

    @SerializedName("height")
    @Expose
    val height: Int,

    @SerializedName("iso_639_1")
    @Expose
    val iso6391: String,

    @SerializedName("file_path")
    @Expose
    val filePath: String,

    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double,

    @SerializedName("vote_count")
    @Expose
    val voteCount: Int,

    @SerializedName("width")
    @Expose
    val width: Int,

    )
