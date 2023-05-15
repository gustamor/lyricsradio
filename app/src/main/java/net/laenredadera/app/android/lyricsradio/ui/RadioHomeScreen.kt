package net.laenredadera.app.android.lyricsradio.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import net.laenredadera.app.android.lyricsradio.ui.model.RadioStationModel

@Composable
fun RadioHomeScreen(radioStationsViewModel: RadioStationViewModel) {
    radioStationsViewModel.getStations()

    Box(modifier = Modifier.fillMaxSize()) {
        RadioStationsList(radioStationsViewModel)
    }
}

@Composable
fun RadioStationsList(radioStationsViewModel: RadioStationViewModel) {

    val stations:List<RadioStationModel>? by radioStationsViewModel.stations.observeAsState()
    Log.i("GusMor", radioStationsViewModel.stations.value.toString())

    Box(
        Modifier
            .background(Color.Blue)
            .fillMaxSize()) {
        LazyColumn{
            items(stations.orEmpty(), key = {it.id}) { station -> ItemStation(station,radioStationsViewModel)}
        }
    }
}

@Composable
fun ItemStation(station: RadioStationModel, radioStationsViewModel: RadioStationViewModel) {

    Card() {
        Row(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.Red)) {
            StationCover(station.cover)
            Column(){
                Text(text = station.name)
                Text(text = station.description ?: "")
            }
        }
    }
}

@Composable
fun StationCover(cover: String) {
   Box(
       Modifier
           .background(Color.Blue)
           .height(30.dp)
           .width(40.dp)){

   }
}

