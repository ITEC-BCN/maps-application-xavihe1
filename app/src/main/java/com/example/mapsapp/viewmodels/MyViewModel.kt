package com.example.mapsapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mapsapp.data.Marker

class MyViewModel: ViewModel() {
    private val _markers = MutableLiveData<Marker>()
    val markers = _markers

    fun getMarkers() {

    }
}