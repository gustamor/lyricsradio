package net.laenredadera.app.android.lyricsradio.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun RadioHomeScreen(radioStationViewModel: RadioStationViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        RadioList(radioStationViewModel)
    }
}

@Composable
fun RadioList(radioStationViewModel: RadioStationViewModel) {

    val stations = radioStationViewModel.stations
    LazyColumn{
        items()
    }
}
