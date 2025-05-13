package com.example.mapsapp.ui.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mapsapp.R

@Composable
fun CreateMarkerScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = "",
            onValueChange = { /*TODO*/ },
            label = { "Title: " }
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = "",
            onValueChange = { /*TODO*/ },
            label = { "Description: " }
        )

        Spacer(modifier = Modifier.height(15.dp))

        Image(
            painter = painterResource(id = R.drawable.camera),
            contentDescription = "Image",
            modifier = Modifier
                .size(200.dp)
                .clickable {  }
        )

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Add Marker")
        }
    }
}