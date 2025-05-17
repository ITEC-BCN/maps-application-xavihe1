package com.example.mapsapp.viewmodels

import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mapsapp.data.Marker
import com.example.mapsapp.data.MyApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

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

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertNewMarker(name: String, description: String, image: Bitmap?) {
        val stream = ByteArrayOutputStream()
        image?.compress(Bitmap.CompressFormat.PNG, 0, stream)
        CoroutineScope(Dispatchers.IO).launch {
            val imageName = database.uploadImage(stream.toByteArray())
            database.insertMarker(name, description, 0.0, 0.0, imageName)
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
    private val _selectedMarker = MutableLiveData<Marker?>()
    val selectedStudent = _selectedMarker

    fun updateStudent(id: String, name: String, description: String, image: Bitmap?){
        val stream = ByteArrayOutputStream()
        image?.compress(Bitmap.CompressFormat.PNG, 0, stream)
        val imageName = _selectedMarker.value?.image?.removePrefix("https://aobflzinjcljzqpxpcxs.supabase.co/storage/v1/object/public/images/")
        CoroutineScope(Dispatchers.IO).launch {
            database.updateMarker(id, name, description, 0.0, 0.0, imageName.toString(), stream.toByteArray())
        }
    }


    fun getMarker(id: String){
        if(_selectedMarker.value == null){
            CoroutineScope(Dispatchers.IO).launch {
                val student = database.getMarker(id)
                withContext(Dispatchers.Main) {
                    _selectedMarker.value = student
                    _markerName.value = student.name
                    _markerDescription.value = student.description
                }
            }
        }
    }

    //Funció DELETE marker
    fun deleteMarker(id: String, image: String){
        CoroutineScope(Dispatchers.IO).launch {
            database.deleteImage(image)
            database.deleteMarker(id)
            getAllMarkers()
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun insertNewMarker(name: String, description: String, longitude: String, latitude: String, image: Bitmap?) {
        val stream = ByteArrayOutputStream()
        image?.compress(Bitmap.CompressFormat.PNG, 0, stream)
        CoroutineScope(Dispatchers.IO).launch {
            val imageName = database.uploadImage(stream.toByteArray())
            database.insertMarker(name, description, longitude.toDouble(), latitude.toDouble(), imageName)
        }
    }




}