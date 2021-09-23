package com.bytestechno.tentwenty_movie.ui.activities.movieDetail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bytestechno.tentwenty_movie.data.models.slider.Poster
import com.bytestechno.tentwenty_movie.data.models.slider.SliderItem
import com.bytestechno.tentwenty_movie.databinding.MovieSliderItemBinding
import com.smarteist.autoimageslider.SliderViewAdapter


internal class SliderAdapter(var context: Context) :
    SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {

    private var mSliderItems: List<Poster> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: MovieSliderItemBinding = MovieSliderItemBinding.inflate(layoutInflater)
        return SliderAdapterVH(binding)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        viewHolder.bind(mSliderItems[position])
    }

    override fun getCount(): Int {
        if (mSliderItems.count() > 5){
            return 5
        }
        return mSliderItems.count()
    }

    internal inner class SliderAdapterVH(var view: MovieSliderItemBinding) : ViewHolder(view.root) {

        fun bind(item: Poster){
            Glide.with(context)
                .load("https://www.themoviedb.org/t/p/w440_and_h660_face"+item.filePath)
                .fitCenter()
                .into(view.moviePoster)
        }
    }

    fun renewItems(sliderItems: List<Poster>) {
        this.mSliderItems = sliderItems
        notifyDataSetChanged()
    }
}