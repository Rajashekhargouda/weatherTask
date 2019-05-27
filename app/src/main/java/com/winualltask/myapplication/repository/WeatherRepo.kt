package com.winualltask.myapplication.repository

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.winualltask.myapplication.model.WeatherModel

@Database(entities = [WeatherModel::class],version = 1)
@TypeConverters(WeatherTypeConverters::class)
abstract class WeatherRepo: RoomDatabase() {
    abstract fun getWeatherDao():WeatherDao

    companion object {
        private const val dbName = "weatherdatabase"
        private var INSTANCE:WeatherRepo?=null

        fun getRepo(): WeatherRepo?{
            return INSTANCE
        }

        fun initRepo(context: Context){
            INSTANCE = Room.databaseBuilder(context,WeatherRepo::class.java, dbName)
                .allowMainThreadQueries().fallbackToDestructiveMigration().build()
            INSTANCE!!
        }
    }
}