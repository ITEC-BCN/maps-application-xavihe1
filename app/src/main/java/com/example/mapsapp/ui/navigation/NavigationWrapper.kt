package com.example.mapsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mapsapp.ui.screens.CreateMarkerScreen
import com.example.mapsapp.ui.screens.MapScreen
import com.example.mapsapp.ui.screens.MarkerListScreen

@Composable
fun NavigationWrapper(navController: NavHostController, padding: Modifier) {
    NavHost(navController, startDestination = Destination.Map) {
        composable<Destination.Map> {
            MapScreen()
        }
        composable<Destination.List> {
            MarkerListScreen()
        }
        composable<Destination.Markers> {
            CreateMarkerScreen()
        }
    }
}