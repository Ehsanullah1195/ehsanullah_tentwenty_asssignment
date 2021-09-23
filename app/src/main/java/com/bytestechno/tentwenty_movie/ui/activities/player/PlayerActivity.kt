package com.bytestechno.tentwenty_movie.ui.activities.player

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bytestechno.tentwenty_movie.R
import com.bytestechno.tentwenty_movie.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding:ActivityPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_player)
        binding.andExoPlayerView.setSource("URL OR FILE ADDRESS");
    }
}