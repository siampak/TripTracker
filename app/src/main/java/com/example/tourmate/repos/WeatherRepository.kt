package com.example.tourmate.repos

import android.location.Location
import com.example.tourmate.model.CurrentWeatherModel
import com.example.tourmate.network.WeatherApi
import com.example.tourmate.network.weather_api_key

class WeatherRepository {

    suspend fun fetchCurrentData(location: Location, tempStatus: Boolean) :CurrentWeatherModel?{

        val endUrl ="weather?lat=${location.latitude}&lon=${location.longitude}&units=metric&appid=$weather_api_key"

        val currentModel =WeatherApi.weatherServiceApi.getCurrentWeather(endUrl)
        return currentModel
    }
}