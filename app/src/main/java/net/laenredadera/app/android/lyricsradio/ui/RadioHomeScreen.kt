package net.laenredadera.app.android.lyricsradio.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.Colors
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import net.laenredadera.app.android.lyricsradio.R
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

    val stations: List<RadioStationModel>? by radioStationsViewModel.stations.observeAsState()
    Log.i("GusMor", radioStationsViewModel.stations.value.toString())

    Box(
        Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        LazyColumn {
            items(stations.orEmpty(), key = { it.id }) { station -> ItemStation() }
        }
    }
}

@Preview
@Composable
fun ItemStation() {

    val darkMode by rememberSaveable { mutableStateOf<Boolean>(false) }
    Card(
        modifier = Modifier
            .shadow(4.dp)
            .testTag("ItemCard")
            .clickable { },

        ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Green),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        )
        {
            Row(
                Modifier
                    .height(100.dp)
                    .padding(8.dp)
                    .background(Color.Red),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                StationCover()
                Column() {
                    Text(text = "Nombre de radio", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(1.dp))
                    Text(text = "Cualquier cosa", fontSize = 13.sp)
                }
            }
            Box(
                Modifier
                    .padding(8.dp)
                    .size(32.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.more_horiz),
                    contentDescription = "MenuHorizImage",

                    colorFilter = if (darkMode) {
                        ColorFilter.tint(Color.White)
                    } else {
                        ColorFilter.tint(Color.Black)
                    }
                )

            }

        }
    }
}
    @Composable
    fun StationCover() {
        Box(
            Modifier
                .padding(8.dp)
                .background(Color.Blue)
                .height(64.dp)
                .width(64.dp)
                .testTag("StationCover")
        ) {
            AsyncImage(
                model = "https://www.easylinedrawing.com/wp-content/uploads/2021/07/log_drawing.png",
                contentDescription = "stationCoverImage"
            )
        }
    }

