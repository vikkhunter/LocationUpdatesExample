package com.example.locationupdatesexample.clean

class WeatherApiRepository(private val weatherApiService: WeatherApiService) : WeatherRepository {
    override suspend fun getWeather(city: String): Weather {
        val response = weatherApiService.getWeatherData(city)
        // Parse the response and return the weather data
        return Weather("", 0.0, "")
    }

    override suspend fun getTemp(): Double {
        return 0.0
    }
}
