package com.example.mapsapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mapsapp.data.MySupabaseClient
import com.example.mapsapp.ui.screens.MyDrawerMenu
import com.example.mapsapp.ui.theme.MapsAppTheme
import com.example.mapsapp.utils.SharedPreferencesHelper
import com.example.mapsapp.viewmodels.AuthViewModel
import com.example.mapsapp.viewmodels.MyViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel = MyViewModel()
        val authViewModel = AuthViewModel(sharedPreferences = SharedPreferencesHelper(this))
        val sharedPreferences = SharedPreferencesHelper(this)
        setContent {
            MapsAppTheme {
                MyDrawerMenu(viewModel, authViewModel)
            }
        }
    }
}