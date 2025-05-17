package com.example.mapsapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mapsapp.data.Marker
import com.example.mapsapp.data.MyApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyViewModel: ViewModel() {
    val database = MyApp.database

    private val _markerName = MutableLiveData<String>()
    val markerName = _markerName
    private val _markerDescription = MutableLiveData<String>()
    val markerDescription = _markerDescription
    private val _markerLatitude = MutableLiveData<String>()
    val markerLatitude = _markerLatitude
    private val _markerLongitude = MutableLiveData<String>()
    val markerLongitude = _markerLongitude

    fun insertNewMarker(name: String, description: String, latitude: String, longitude: String) {
        val newMarker = Marker(name = name, description = description, latitude = latitude.toDouble(), longitude = longitude.toDouble())
        CoroutineScope(Dispatchers.IO).launch {
            database.insertMarker(newMarker)
            getAllMarkers()
        }
    }

    fun editMarkerName(name: String) {
        _markerName.value = name
    }

    fun editMarkerDescription(description: String) {
        _markerDescription.value = description
    }


    private val _markers = MutableLiveData<List<Marker>>()
    val markers = _markers

    fun getAllMarkers() {
        CoroutineScope(Dispatchers.IO).launch {
            val databaseMarkers = database.getAllMarkers()
            withContext(Dispatchers.Main) {
                _markers.value = databaseMarkers
            }
        }
    }

    //Funció UPDATE marker
    private var _selectedMarker: Marker? = null
    fun updateStudent(id: String, name: String, description: String, longitude: String, latitude: String){
        CoroutineScope(Dispatchers.IO).launch {
            database.updateMarker(id, name, description, longitude.toDouble(), latitude.toDouble())
        }
    }
    fun getStudent(id: String){
        if(_selectedMarker == null){
            CoroutineScope(Dispatchers.IO).launch {
                val student = database.getMarker(id)
                withContext(Dispatchers.Main) {
                    _selectedMarker = student
                    _markerName.value = student.name
                    _markerDescription.value = student.description
                }
            }
        }
    }

    //Funció DELETE marker
    fun deleteMarker(id: String){
        CoroutineScope(Dispatchers.IO).launch {
            database.deleteMarker(id)
            getAllMarkers()
        }
    }



}