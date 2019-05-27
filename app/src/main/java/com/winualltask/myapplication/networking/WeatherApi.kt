package com.winualltask.myapplication.networking

import com.winualltask.myapplication.model.WeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    companion object {
        val api: WeatherApi by lazy {
            RetrofitHelper.retrofit.create(WeatherApi::class.java)
        }
    }

    @GET("weather")
    fun getWeather(@Query("appid")appId:String,
                       @Query("q")cityName:String):Call<WeatherModel>



}