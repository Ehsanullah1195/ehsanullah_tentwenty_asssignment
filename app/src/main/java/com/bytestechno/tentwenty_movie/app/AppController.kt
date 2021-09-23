package com.bytestechno.tentwenty_movie.app

import android.app.Application
import com.bytestechno.tentwenty_movie.utils.PreferencesManager

class AppController: Application() {

    override fun onCreate() {
        super.onCreate()
        PreferencesManager.with(this)
    }
}