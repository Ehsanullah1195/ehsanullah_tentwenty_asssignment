package com.bytestechno.tentwenty_movie.ui.activities.movieDetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bytestechno.tentwenty_movie.R
import com.bytestechno.tentwenty_movie.data.models.movieDetail.MovieDetail
import com.bytestechno.tentwenty_movie.data.models.movies.Movie
import com.bytestechno.tentwenty_movie.databinding.ActivityMovieDetailBinding
import com.bytestechno.tentwenty_movie.retrofit.APIClient
import com.bytestechno.tentwenty_movie.retrofit.APIInterface
import com.bytestechno.tentwenty_movie.ui.activities.bookTicket.BookTicketActivity
import com.bytestechno.tentwenty_movie.ui.activities.dashboard.MainVM
import com.bytestechno.tentwenty_movie.ui.activities.player.PlayerActivity
import com.bytestechno.tentwenty_movie.ui.base.ViewModelFactory
import com.bytestechno.tentwenty_movie.utils.Constants
import com.bytestechno.tentwenty_movie.utils.PreferencesManager
import com.bytestechno.tentwenty_movie.utils.Status
import com.smarteist.autoimageslider.SliderAnimations

class MovieDetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding:ActivityMovieDetailBinding
    private lateinit var detailViewModel:MovieDetailVM
    private lateinit var adapter: SliderAdapter
    private val tags:String = "movieDetail"
    private var movieId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
        movieId = intent.extras?.get("id") as Int

        val apiInterface: APIInterface = APIClient.getClient()!!.create(APIInterface::class.java)
        detailViewModel = ViewModelProvider(this, ViewModelFactory(apiInterface)).get(MovieDetailVM::class.java)
        detailViewModel.movieDetail(movieId, "a5c23d52c513d5308b1944a0e862e784")
        detailViewModel.sliderPoster(movieId, "a5c23d52c513d5308b1944a0e862e784")

        detailViewModel.getMovieDetail().observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    Log.d(tags, "Loading")
                    binding.loader.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    binding.loader.visibility = View.GONE
                    Log.d(tags, "Success")
                    it.data.let { detail ->
                        binding.detail = detail
                    }
                }
                Status.ERROR -> {
                    Log.d(tags, it.code.toString())
                    //Handle Error
                    binding.loader.visibility = View.GONE
                    val localData = PreferencesManager.get<MovieDetail>(Constants.DETAILS)
                    localData.let { movie ->
                        binding.detail = movie
                    }
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })

        adapter = SliderAdapter(this)
        binding.slider.setSliderAdapter(adapter)

        detailViewModel.getPosters().observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    Log.d(tags, "Loading")
                    //progressBar.visibility = View.VISIBLE
                    //recyclerView.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    //progressBar.visibility = View.GONE
                    Log.d(tags, "Success")
                    it.data.let { item ->
                        adapter.renewItems(item!!.posters)
                    }


                }
                Status.ERROR -> {
                    Log.d(tags, it.code.toString())
                    Log.d(tags, it.message!!)
                    //Handle Error
                    //progressBar.visibility = View.GONE

                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })

        binding.btnTrailer.setOnClickListener(this)
        binding.btnBookMovie.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_trailer -> {
                startActivity(Intent(this, PlayerActivity::class.java))
            }
            R.id.btn_book_movie -> {
                startActivity(Intent(this, BookTicketActivity::class.java)
                    .putExtra("id", movieId)
                    .putExtra("name", detailViewModel.getMovieDetail().value?.data?.originalTitle)
                    .putExtra("date", detailViewModel.getMovieDetail().value?.data?.releaseDate))
            }
        }
    }
}