package com.winualltask.myapplication

import android.app.Application
import com.winualltask.myapplication.networking.RetrofitHelper
import com.winualltask.myapplication.repository.WeatherRepoHelper

class WeatherApp: Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init(){
        RetrofitHelper.initialize(BuildConfig.BASE_URL)
        WeatherRepoHelper.initWeatherRepo(this)

    }
}