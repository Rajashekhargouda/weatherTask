package com.winualltask.myapplication.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.winualltask.myapplication.R
import com.winualltask.myapplication.model.WeatherModel
import com.winualltask.myapplication.networking.ProcessRequestResponse
import com.winualltask.myapplication.networking.WeatherApi
import com.winualltask.myapplication.networking.process
import com.winualltask.myapplication.repository.SharedPrefHelper
import com.winualltask.myapplication.repository.WeatherRepoHelper
import com.winualltask.myapplication.util.DateUtil
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import java.util.*

class WeatherViewModel: ViewModel() {
    var weatherLiveData = MutableLiveData<WeatherResponse>()

    fun getWeatherData(
        appId: String,
        cityName: String,
        context: Context){
        try {
            weatherLiveData.value = WeatherResponse.Loading

            launch(UI){
                weatherLiveData.value =  async(CommonPool) {
                    fetchDataFromServer(appId,cityName,context) }.await()
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    // fetches the data data from the server
    private fun fetchDataFromServer(appId: String,locationName:String,context: Context):WeatherResponse?{
        try {
            val result = process(WeatherApi.api.getWeather(appId = appId,cityName = locationName))
            when(result){
                is ProcessRequestResponse.Success<*> ->{
                    val weatherData = result.success as WeatherModel
                    saveData(weatherData)
                    SharedPrefHelper.storeLogInPref(context,Calendar.getInstance().timeInMillis)
                    return WeatherResponse.Success(weatherData)
                }
                is ProcessRequestResponse.Failure ->{
                    return WeatherResponse.Failure(result.msgId)
                }
            }
            return null
        }catch (e:Exception){
            e.printStackTrace()
            return null
        }
    }

   private fun saveData(weatherModel: WeatherModel){
        launch (CommonPool) {
            try {
                WeatherRepoHelper.storeWeatherData(weatherModel)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    fun getSavedData(){
        weatherLiveData.value = WeatherResponse.Loading
        launch(UI) {
            try {
                val result =  async(CommonPool) {
                    WeatherRepoHelper.getWeatherData()
                }.await()

                result?.let {
                    weatherLiveData.value = WeatherResponse.Success(it)
                }?:kotlin.run {
                    weatherLiveData.value = WeatherResponse.Failure(R.string.something_went_wrong)
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    /*takes the logged data from shared preference and decides whether to make an API call
    * */
    fun shouldCallAPI(context:Context):Boolean{
        val logTime = SharedPrefHelper.getLogFromPref(context)
        return isDataExpired(logTime)
    }

    // validates whether the logged time has exceeded 24hrs
    private fun isDataExpired(logTime:Long):Boolean{
        return if (logTime>0){
            DateUtil.checkIfGreaterThanDay(logTime)
        }else true
    }



    sealed class WeatherResponse{
        data class Success(var weatherData: WeatherModel): WeatherResponse()
        data class Failure(var msg:Int): WeatherResponse()
        object Loading: WeatherResponse()
    }
}