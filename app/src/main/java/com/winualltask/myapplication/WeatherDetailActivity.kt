package com.winualltask.myapplication

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.winualltask.myapplication.model.WeatherModel
import com.winualltask.myapplication.repository.SharedPrefHelper
import com.winualltask.myapplication.util.Constants
import com.winualltask.myapplication.util.DateUtil
import com.winualltask.myapplication.util.hide
import com.winualltask.myapplication.util.visible
import com.winualltask.myapplication.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_weather_detail.*

class WeatherDetailActivity : AppCompatActivity() {
    private lateinit var weatherViewModel:WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_detail)
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
        setupObserver()
        setToolbar()
    }

    private fun setToolbar() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.title_wetaher_detail)
    }

    private fun setupObserver(){
        weatherViewModel.weatherLiveData.observe(this, Observer {
            when(it){
                is WeatherViewModel.WeatherResponse.Failure ->{
                    pb.hide()
                    ct_lt_data.hide()
                    txt_error.visible()
                    txt_error.text = getString(it.msg)
                }
                is WeatherViewModel.WeatherResponse.Success ->{
                    pb.hide()
                    txt_error.hide()
                    ct_lt_data.visible()
                    initUI(it.weatherData)
                    Log.e("success",""+it.weatherData.toString())
                }
                is  WeatherViewModel.WeatherResponse.Loading ->{
                    pb.visible()
                }
            }
        })
    }


    private fun initUI(weatherData:WeatherModel){
        txt_place.text = weatherData.name
        txt_temp.text = weatherData.main.temp.toString()
        txt_weather_type.text = weatherData.weatherList[0].main
        txt_descr.text = weatherData.weatherList[0].description
        txt_humid.text = weatherData.main.humidity.toString() + " %"
        txt_wind.text = weatherData.wind.speed.toString()
        txt_updated_on.text = DateUtil.getDateString(SharedPrefHelper.getLogFromPref(this))
    }

    // decides whether API call to be made or not
    private fun checkForData(cityName:String){
       if (weatherViewModel.shouldCallAPI(this)){
           weatherViewModel.getWeatherData(Constants.APP_ID,cityName,this)
       }else weatherViewModel.getSavedData()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }


    override fun onResume() {
        super.onResume()
        try {
            checkForData(intent.getStringExtra(Constants.CITY_NAME_KEY))
        }catch (e:Exception){
            e.printStackTrace()
        }

    }
}
