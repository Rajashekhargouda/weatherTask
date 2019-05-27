
package com.winualltask.myapplication.model
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "weather")
data class WeatherModel(
    @SerializedName("id") @PrimaryKey val id: Long,
    @SerializedName("base") val base: String,
    @SerializedName("clouds")val clouds: Clouds,
    @SerializedName("cod")val code: Int,
    @SerializedName("coord")val coordinates: Coordinates,
    @SerializedName("dt")val dt: Long,
    @SerializedName("main")val main: Main,
    @SerializedName("name")val name: String,
    @SerializedName("sys")val sys: Sys,
    @SerializedName("visibility")val visibility: Long,
    @SerializedName("weather")val weatherList: List<Weather>,
    @SerializedName("wind")val wind: Wind
)