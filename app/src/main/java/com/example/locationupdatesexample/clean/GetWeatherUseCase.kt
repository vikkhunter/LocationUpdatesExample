package com.example.locationupdatesexample.clean

class GetWeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend fun execute(city: String): Weather {
        return weatherRepository.getWeather(city)
    }
}
