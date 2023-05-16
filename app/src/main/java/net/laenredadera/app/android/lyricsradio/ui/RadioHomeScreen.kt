package net.laenredadera.app.android.lyricsradio.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
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
            .background(Color.Black)
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
                .padding(8.dp)
                .background(Color.Red),
            verticalAlignment = Alignment.CenterVertically
        ) {
            StationCover(station.cover)
            Column(

            ){
                Text(text = station.name, fontSize=15.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(1.dp))
                Text(text = station.description ?: "", fontSize=13.sp)
            }
            }
        }

}


@Composable
fun StationCover(cover: String) {
   Box(
       Modifier.padding(8.dp)
           .background(Color.Blue)
           .height(64.dp)
           .width(64.dp)){
       AsyncImage(
           model = "https://scontent-mad2-1.xx.fbcdn.net/v/t39.30808-6/304805502_502835028513101_5185818003385218019_n.png?_nc_cat=108&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=QlW1H4lsz9oAX-P-wJH&_nc_ht=scontent-mad2-1.xx&oh=00_AfBIZSPKSN6__9yL4w91Z0vN0c2juaD6q59uNDk4Lva24w&oe=6466B3AE",
           contentDescription = "stationCoverImage")
   }
}

