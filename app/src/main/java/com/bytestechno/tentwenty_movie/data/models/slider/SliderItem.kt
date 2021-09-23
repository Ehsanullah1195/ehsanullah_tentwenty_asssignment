package com.bytestechno.tentwenty_movie.data.models.slider

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SliderItem(
    @SerializedName("backdrops")
    @Expose
    val backdrops: List<Backdrop>,

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("logos")
    @Expose
    val logos: List<Logo>,

    @SerializedName("posters")
    @Expose
    val posters: List<Poster>,
)