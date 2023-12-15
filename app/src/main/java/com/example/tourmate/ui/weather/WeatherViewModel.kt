package com.example.tourmate.ui.weather

import android.location.Location
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tourmate.model.CurrentWeatherModel
import com.example.tourmate.repos.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val TAG = "WeatherViewModel"
    private val repository = WeatherRepository()
    private val _currentData = MutableLiveData<CurrentWeatherModel>()


    var tempStatus = false

    val current: LiveData<CurrentWeatherModel>
        get() = _currentData


    fun getWeatherData(location: Location) {

        viewModelScope.launch {
            try {

                _currentData.value = repository.fetchCurrentData(location, tempStatus)

            }catch (e: Exception) {
                Log.e(TAG,e.localizedMessage)

            }
        }


    }
}