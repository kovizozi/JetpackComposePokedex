package com.plcoding.jetpackcomposepokedex

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.plant

import java.util.*

@HiltAndroidApp // this is for hilt
class PokedexApplication : Application() { // add this to manifest as name". pokedex application
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}