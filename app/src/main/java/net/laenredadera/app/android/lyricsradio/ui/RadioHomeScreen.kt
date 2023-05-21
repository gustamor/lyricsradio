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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Colors
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import net.laenredadera.app.android.lyricsradio.R
import net.laenredadera.app.android.lyricsradio.ui.model.RadioStationModel

@Composable
fun RadioHomeScreen(radioStationsViewModel: RadioStationViewModel) {
    radioStationsViewModel.getStations()

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),) {
        RadioStationsList(radioStationsViewModel)
    }
}

@Composable
fun RadioStationsList(radioStationsViewModel: RadioStationViewModel) {

    val stations: List<RadioStationModel>? by radioStationsViewModel.stations.observeAsState()
    Log.i("GusMor", radioStationsViewModel.stations.value.toString())

    Box(
        Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        LazyColumn {
            items(stations.orEmpty(), key = { it.id }) { station -> if (station.enabled) ItemStation(station) }
        }
    }
}

@Composable
fun ItemStation(station: RadioStationModel) {

    val darkMode by rememberSaveable { mutableStateOf<Boolean>(true) }
    Card(
        modifier = Modifier
            .shadow(4.dp)
            .testTag("ItemCard")
            .background(MaterialTheme.colorScheme.background)
            .clickable { /* TODO */},

        ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(92.dp)
            .background(MaterialTheme.colorScheme.background),

        verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        )
        {
            Row(
                Modifier
                    .height(92.dp)
                    .padding(4.dp)
                .background(MaterialTheme.colorScheme.background),

            verticalAlignment = Alignment.CenterVertically
            ) {
                StationCover(station.cover)
                Column() {
                    Text(text = station.name,  fontSize = 15.sp, fontWeight = FontWeight.Bold, modifier = Modifier.testTag("TextItemTitle"))
                    Spacer(modifier = Modifier.height(1.dp))
                    Text(text = station.description ?: "cualquiera ", fontSize = 13.sp, modifier = Modifier.testTag("TextItemDescription"))
                }
            }
            Box(
                Modifier
                    .padding(16.dp)
                    .size(32.dp)
                    .clickable { /*TODO*/ }
                    .background(MaterialTheme.colorScheme.background)
                    .testTag("MenuHorizontalItem")
            ) {
                Image(
                    painter = painterResource(id = R.drawable.more_horiz),
                    contentDescription = "MenuHorizImage",
                    colorFilter =  ColorFilter.tint(MaterialTheme.colorScheme.tertiary)
                )
            }
        }
    }
}
@Composable
fun StationCover(url: String) {
    Box(
        Modifier
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.tertiary)
            .height(64.dp)
            .width(64.dp)
            .testTag("StationCover")
    ) {

        SubcomposeAsyncImage(
            model = url,
            contentDescription = "stationCoverImage",
            contentScale = ContentScale.FillBounds,
            ) {
            val state = painter.state
            if (state is AsyncImagePainter.State.Loading) {
                CircularProgressIndicator(color = Color.Red, modifier = Modifier.fillMaxSize().padding(4.dp))
            }
            else if (state is AsyncImagePainter.State.Error || state is AsyncImagePainter.State.Empty){
                Image(painter = painterResource(id = R.drawable.blur), modifier = Modifier.fillMaxSize(), contentDescription = "imagenBlur")
            }
            else  {
                SubcomposeAsyncImageContent()
            }
        }

    }
}

