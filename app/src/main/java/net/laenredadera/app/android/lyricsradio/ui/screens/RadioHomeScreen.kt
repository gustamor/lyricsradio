package net.laenredadera.app.android.lyricsradio.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import net.laenredadera.app.android.lyricsradio.ui.vm.PlayerViewModel
import net.laenredadera.app.android.lyricsradio.ui.vm.RadioStationViewModel
import net.laenredadera.app.android.lyricsradio.ui.composables.StationItem
import net.laenredadera.app.android.lyricsradio.ui.model.RadioStationModelUI


@Composable
fun RadioHomeScreen(
    navigationController: NavHostController,
    radioStationsViewModel: RadioStationViewModel,
    playerViewModel: PlayerViewModel
) {
    radioStationsViewModel.getStations()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background),) {
        RadioStationsList(radioStationsViewModel,navigationController,playerViewModel)
    }
}

@Composable
fun RadioStationsList(
    radioStationsViewModel: RadioStationViewModel,
    navigationController: NavHostController,
    playerViewModel: PlayerViewModel
) {

    val stations: List<RadioStationModelUI>? by radioStationsViewModel.stations.observeAsState()
    Log.i("GusMor", radioStationsViewModel.stations.value.toString())

    Box(
        Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        LazyColumn {
            items(stations.orEmpty(),key = { it.id }) { station -> if (station.enabled) StationItem(station,navigationController,playerViewModel) }
        }
    }
}
