package com.example.tourmate.ui.weather

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tourmate.userlocation.isLocationPermissionGranted
import com.example.tourmate.userlocation.requestLocationPermission

import com.example.tourmate.databinding.FragmentWeatherBinding
import com.example.tourmate.userlocation.LocationViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class WeatherFragment : Fragment() {

    private lateinit var binding: FragmentWeatherBinding
    private lateinit var client: FusedLocationProviderClient
    private val locationViewModel: LocationViewModel by viewModels()
    private val weatherViewModel: WeatherViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        client = LocationServices.getFusedLocationProviderClient(requireActivity())

        binding = FragmentWeatherBinding.inflate(inflater, container, false)

        locationViewModel.location.observe(viewLifecycleOwner) {

            weatherViewModel.getWeatherData(it)
        }


        //get from repository = weatherServiceApi.getCurrentWeather(endUrl)
        weatherViewModel.current.observe(viewLifecycleOwner) {

            binding.current = it
        }


        //Alert Dialog Button Create(LocalUtils.kt)
        if (isLocationPermissionGranted(requireActivity())){
            detectUserLocation()
        }else {
            requestLocationPermission(requireActivity())
        }
        return binding.root
    }


//    permission check (LocalUtils.kt)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if(requestCode == 999){
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                detectUserLocation()
            }else{
                //show meaningful information
            }
        }
    }



    private fun detectUserLocation() {

        client.lastLocation.addOnSuccessListener {
            locationViewModel.setNewLocation(it)
        }
    }




}