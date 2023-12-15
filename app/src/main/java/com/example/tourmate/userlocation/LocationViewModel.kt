package com.example.tourmate.userlocation

import android.health.connect.datatypes.ExerciseRoute
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.protobuf.DescriptorProtos


class LocationViewModel :ViewModel() {
    private val _location =MutableLiveData<Location>()


    val location : LiveData<Location>
        get() = _location


    fun setNewLocation(location: Location){
        _location.value = location
    }
}