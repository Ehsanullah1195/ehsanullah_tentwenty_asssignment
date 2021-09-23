package com.bytestechno.tentwenty_movie.ui.activities.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bytestechno.tentwenty_movie.R
import com.bytestechno.tentwenty_movie.data.models.movies.Movie
import com.bytestechno.tentwenty_movie.databinding.ActivityMainBinding
import com.bytestechno.tentwenty_movie.retrofit.APIClient
import com.bytestechno.tentwenty_movie.retrofit.APIInterface
import com.bytestechno.tentwenty_movie.ui.base.ViewModelFactory
import com.bytestechno.tentwenty_movie.utils.Constants.MOVIES
import com.bytestechno.tentwenty_movie.utils.PreferencesManager
import com.bytestechno.tentwenty_movie.utils.Status

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var mainViewModel:MainVM
    private lateinit var adapter:MovieAdapter
    private val tags:String = "movieLists"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        adapter = MovieAdapter(this)
        binding.rcvMovies.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        binding.rcvMovies.layoutManager = layoutManager
        val apiInterface: APIInterface = APIClient.getClient()!!.create(APIInterface::class.java)
        mainViewModel = ViewModelProvider(this, ViewModelFactory(apiInterface)).get(MainVM::class.java)
        mainViewModel.getMovies().observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    Log.d(tags, "Loading")
                    binding.loader.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    binding.loader.visibility = View.GONE
                    Log.d(tags, "Success")
                    it.data.let { movie ->
                        adapter.updateList(movie!!.results)
                    }


                }
                Status.ERROR -> {
                    Log.d(tags, it.code.toString())
                    binding.loader.visibility = View.GONE
                    val localData = PreferencesManager.get<Movie>(MOVIES)
                    localData.let { movie ->
                        adapter.updateList(movie!!.results)
                    }
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })

    }
}