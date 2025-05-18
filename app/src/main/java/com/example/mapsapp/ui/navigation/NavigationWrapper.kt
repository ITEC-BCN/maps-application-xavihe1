package com.example.mapsapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mapsapp.PermissionScreen
import com.example.mapsapp.ui.screens.CameraScreen
import com.example.mapsapp.ui.screens.CreateMarkerScreen
import com.example.mapsapp.ui.screens.LoginScreen
import com.example.mapsapp.ui.screens.MapScreen
import com.example.mapsapp.ui.screens.MarkerListScreen
import com.example.mapsapp.ui.screens.RegisterScreen
import com.example.mapsapp.viewmodels.AuthViewModel
import com.example.mapsapp.viewmodels.MyViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationWrapper(navController: NavHostController, padding: Modifier, myViewModel: MyViewModel, authViewModel: AuthViewModel) {
    NavHost(navController, startDestination = Destination.PermissionScreen) {
        composable<Destination.PermissionScreen> {
            PermissionScreen() {
                navController.navigate(Destination.Map)
            }
        }
        composable<Destination.Map> {
            MapScreen() {
                navController.navigate(Destination.Markers)
            }
        }
        composable<Destination.List> {
            MarkerListScreen(myViewModel) {
                navController.navigate(Destination.Detail(it))
            }
        }
        composable<Destination.Markers> {
            CreateMarkerScreen(myViewModel) {
                navController.navigate(Destination.Camera)
            }
        }
        composable<Destination.Camera> {
            CameraScreen()
        }
        composable<Destination.Login> {
            LoginScreen(authViewModel) {
                navController.navigate(Destination.Map)
            }
        }
        composable<Destination.Register> {
            //RegisterScreen(authViewModel)
        }
    }
}