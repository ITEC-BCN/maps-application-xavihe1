package com.example.mapsapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mapsapp.data.Marker
import com.example.mapsapp.viewmodels.MyViewModel

@Composable
fun MarkerListScreen(myViewModel: MyViewModel) {
    val markers by myViewModel.markers.observeAsState(emptyList<Marker>())
    myViewModel.getAllMarkers()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(0.6f)
        ) {
            items(markers) {marker ->
                val dismissState = rememberSwipeToDismissBoxState()
                if (dismissState.currentValue == SwipeToDismissBoxValue.EndToStart &&
                    dismissState.targetValue == SwipeToDismissBoxValue.EndToStart) {
                    LaunchedEffect(Unit) {
                        myViewModel.deleteMarker(marker.id.toString(), marker.image.toString())
                    }
                }
                SwipeToDismissBox(state = dismissState, backgroundContent = {
                    Box(Modifier.fillMaxSize().background(Color.Red),contentAlignment = Alignment.BottomEnd) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                    }
                }) {
                    MarkerItem(marker)
                }
            }
        }
    }
}

@Composable
fun MarkerItem(marker: Marker) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp)
            .clickable {

            }
    ) {
        Row {
            Text(marker.name)
        }
    }
}