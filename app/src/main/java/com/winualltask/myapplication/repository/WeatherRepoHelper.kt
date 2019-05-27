package com.winualltask.myapplication.repository

import android.content.Context
import com.winualltask.myapplication.model.WeatherModel

class WeatherRepoHelper {

     companion object {
         fun initWeatherRepo(context: Context){
             WeatherRepo.initRepo(context)
         }

         fun storeWeatherData(weatherModel: WeatherModel){
            val weatherDao = WeatherRepo.getRepo()?.getWeatherDao()
            weatherDao?.insertWeather(weatherModel)
        }

         fun getWeatherData():WeatherModel?{
             val weatherDao = WeatherRepo.getRepo()?.getWeatherDao()
             return weatherDao?.getWeather()
         }
    }
}