package com.example.mapsapp.data

import kotlinx.serialization.Serializable

@Serializable
data class Marker(
    val id: Int? = null,
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double
)
