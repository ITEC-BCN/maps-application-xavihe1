package com.example.mapsapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.mapsapp.viewmodels.MyViewModel

@Composable
fun DetailMarkerScreen(markerId: String, myViewModel: MyViewModel){
    myViewModel.getMarker(markerId)
    val markerName: String by myViewModel.markerName.observeAsState("")
    val markerDescription: String by myViewModel.markerDescription.observeAsState("")

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(value = markerName, onValueChange = { myViewModel.editMarkerName(it) })
        TextField(value = markerDescription, onValueChange = { myViewModel.editMarkerDescription(it) })
        Button(onClick = {
            myViewModel.updateMarker(markerId, markerName, markerDescription, null) }
        ) {
            Text("Update")
        }
    }
}
