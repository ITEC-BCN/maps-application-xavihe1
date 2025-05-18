package com.example.mapsapp.ui.navigation

import kotlinx.serialization.Serializable

sealed class Destination {
    @Serializable
    object PermissionScreen: Destination() //Permisos
    @Serializable
    object Map: Destination() //Mapa

    @Serializable
    object List: Destination() //Lista de markers

    @Serializable
    object Markers: Destination() //Crear markers

    @Serializable
    object Login: Destination() //Login

    @Serializable
    object Register: Destination() //Register

    @Serializable
    object Camera: Destination() //Camera

    @Serializable
    data class Detail(val id: Int): Destination() //Detail marker
}