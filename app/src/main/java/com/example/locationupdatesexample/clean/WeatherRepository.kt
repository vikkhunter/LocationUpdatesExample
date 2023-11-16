package com.example.locationupdatesexample.clean

interface WeatherRepository {
    suspend fun getWeather(city: String): Weather
    suspend fun getTemp(): Double
}
