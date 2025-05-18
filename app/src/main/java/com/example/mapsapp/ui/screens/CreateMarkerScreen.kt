package com.example.mapsapp.ui.screens

import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mapsapp.viewmodels.MyViewModel
import com.example.mapsapp.R

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateMarkerScreen(myViewModel: MyViewModel, navigateCamera: () -> Unit){
    val markerName: String by myViewModel.markerName.observeAsState("")
    val markerDescription: String by myViewModel.markerDescription.observeAsState("")
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = markerName,
            onValueChange = { myViewModel.editMarkerName(it) },
            label = { Text("Name: ") }
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = markerDescription,
            onValueChange = { myViewModel.editMarkerDescription(it) },
            label = { Text("Description: ") }
        )

        Spacer(modifier = Modifier.height(15.dp))

        Image(
            painter = painterResource(id = R.drawable.camera),
            contentDescription = "Image",
            modifier = Modifier
                .size(100.dp)
                .clickable { navigateCamera() }
        )

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = { myViewModel.insertNewMarker(markerName, markerDescription, bitmap.value) }
        ) {
            Text(text = "Add Marker")
        }
    }
}