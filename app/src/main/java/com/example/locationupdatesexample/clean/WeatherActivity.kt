package com.example.locationupdatesexample.clean

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.locationupdatesexample.R

class WeatherActivity : AppCompatActivity() {

     lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        viewModel.weather.observe(this) {
            //UI
        }
    }
}
