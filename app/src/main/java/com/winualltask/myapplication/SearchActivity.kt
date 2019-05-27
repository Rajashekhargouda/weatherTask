package com.winualltask.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.widget.Toast
import com.winualltask.myapplication.util.Constants
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        sv_city_name.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0!=null && p0.isNotEmpty())
                    navigateToDetailActivity(p0)
                else showToast(getString(R.string.enter_city_name))
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
    }

    fun navigateToDetailActivity(cityName:String){
        val intent = Intent(this,WeatherDetailActivity::class.java)
        intent.putExtra(Constants.CITY_NAME_KEY,cityName)
        startActivity(intent)
    }

    fun showToast(msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

}
