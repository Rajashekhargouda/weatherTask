package com.winualltask.myapplication.repository

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.winualltask.myapplication.model.WeatherModel


@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weatherModel: WeatherModel)

    @Query("select * from weather")
    fun getWeather():WeatherModel

}