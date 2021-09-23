package com.bytestechno.tentwenty_movie.data.models.movies

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bytestechno.tentwenty_movie.R
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieData(
        @SerializedName("adult")
        @Expose
        val adult:Boolean,

        @SerializedName("backdrop_path")
        @Expose
        val backdropPath:String,

        @SerializedName("genre_ids")
        @Expose
        val genreIds:List<Int>,

        @SerializedName("id")
        @Expose
        val id:Int,

        @SerializedName("original_language")
        @Expose
        val originalLanguage:String,

        @SerializedName("original_title")
        @Expose
        val originalTitle:String,

        @SerializedName("overview")
        @Expose
        val overview:String,

        @SerializedName("popularity")
        @Expose
        val popularity:Double,

        @SerializedName("poster_path")
        @Expose
        val  posterPath:String,

        @SerializedName("release_date")
        @Expose
        val releaseDate:String,

        @SerializedName("title")
        @Expose
        val title:String,

        @SerializedName("video")
        @Expose
        val video:Boolean,

        @SerializedName("vote_average")
        @Expose
        val voteAverage:Double,

        @SerializedName("vote_count")
        @Expose
        val voteCount:Int,

        )

@BindingAdapter("app:imageUrl")
fun loadImage(imageView: ImageView, url: String?) {
        Glide.with(imageView.context).load("https://www.themoviedb.org/t/p/w440_and_h660_face$url").centerCrop()
                .placeholder(R.drawable.default_poster)
                .into(imageView)
}