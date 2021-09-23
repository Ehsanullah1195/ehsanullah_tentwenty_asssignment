package com.bytestechno.tentwenty_movie.ui.activities.dashboard

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bytestechno.tentwenty_movie.data.models.movies.MovieData
import com.bytestechno.tentwenty_movie.databinding.MovieItemBinding
import com.bytestechno.tentwenty_movie.ui.activities.movieDetail.MovieDetailActivity

internal class MovieAdapter(private val context: Context) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var list: MutableList<MovieData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    internal inner class ViewHolder(private val view: MovieItemBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(item: MovieData){
            view.movie = item
            view.executePendingBindings()
            view.btnBook.setOnClickListener {
                context.startActivity(Intent(context, MovieDetailActivity::class.java).putExtra("id", item.id))
            }

        }

    }

    fun updateList(list: List<MovieData>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

}