package com.winualltask.myapplication.repository

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.winualltask.myapplication.model.*
import com.winualltask.myapplication.util.fromJsonString
import java.util.*

class WeatherTypeConverters {
    var gson = Gson()


    @TypeConverter
    fun stringToWeatherList(data:String?):List<Weather>{
        data?.let {
            val listType = object : TypeToken<List<Weather>>() {}.type
            return gson.fromJson(data,listType)
        }?:kotlin.run {
            return Collections.emptyList()
        }
    }

    @TypeConverter
    fun weatherListToString(weatherList: List<Weather>):String{
        return gson.toJson(weatherList)
    }

    @TypeConverter
    fun cloudToString(clouds: Clouds):String{
        return gson.toJson(clouds)
    }

    @TypeConverter
    fun stringToClouds(data:String):Clouds{
        return gson.fromJsonString<Clouds>(data)
    }

    @TypeConverter
    fun coordinatesToString(coordinates: Coordinates):String{
        return gson.toJson(coordinates)
    }

    @TypeConverter
    fun stringToCoordinates(data:String):Coordinates{
        return gson.fromJsonString<Coordinates>(data)
    }

    @TypeConverter
    fun MainToString(main: Main):String{
        return gson.toJson(main)
    }

    @TypeConverter
    fun stringToMain(data:String):Main{
        return gson.fromJsonString<Main>(data)
    }

    @TypeConverter
    fun windToString(wind: Wind):String{
        return gson.toJson(wind)
    }

    @TypeConverter
    fun stringToWind(data:String):Wind{
        return gson.fromJsonString<Wind>(data)
    }

    @TypeConverter
    fun sysToString(sys: Sys):String{
        return gson.toJson(sys)
    }

    @TypeConverter
    fun stringToSys(data:String):Sys{
        return gson.fromJsonString<Sys>(data)
    }


}