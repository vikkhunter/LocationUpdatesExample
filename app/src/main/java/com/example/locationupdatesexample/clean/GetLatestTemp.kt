package com.example.locationupdatesexample.clean

class GetLatestTemp constructor(private val repository: WeatherApiRepository) {
    suspend fun getTemp() : Double {
        return repository.getTemp()
    }

    fun getPublicationName() : String {
        return "Main"
    }

}