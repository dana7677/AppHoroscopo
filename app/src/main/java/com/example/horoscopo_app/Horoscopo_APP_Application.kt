package com.example.horoscopo_app

import android.app.Application
import com.example.horoscopo_app.data.Prefs

class Horoscopo_APP_Application:Application() {

    companion object
    {
        lateinit var prefs:Prefs
    }

    override fun onCreate() {
        super.onCreate()
        prefs= Prefs(applicationContext)

    }
}