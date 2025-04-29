package com.example.mapsapp.ui.navigation

import kotlinx.serialization.Serializable

sealed class Destination {
    @Serializable
    object Map: Destination() //Mapa

    @Serializable
    object List: Destination() //Lista de markers

    @Serializable
    object Markers: Destination() //Crear markers
}