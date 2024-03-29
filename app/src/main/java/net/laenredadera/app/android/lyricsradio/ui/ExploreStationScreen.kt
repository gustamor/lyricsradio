package net.laenredadera.app.android.lyricsradio.ui

import android.net.Uri
import android.util.Log
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.laenredadera.app.android.lyricsradio.R
import net.laenredadera.app.android.lyricsradio.Routes
import net.laenredadera.app.android.lyricsradio.ui.model.RadioStationModelUI


@Composable
fun ExploreStationScreen(
    navigationController: NavHostController,
    radioStationsViewModel: RadioStationViewModel,
    playerViewModel: PlayerViewModel
) {
    radioStationsViewModel.getStations()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 21.dp)
            .background(Color(0xFF1C1C1C))
    ) {
        RadioStationsList(radioStationsViewModel, navigationController, playerViewModel)
    }
}

@Composable
fun RadioStationsList(
    radioStationsViewModel: RadioStationViewModel,
    navigationController: NavHostController,
    playerViewModel: PlayerViewModel = hiltViewModel()
) {
    val stations: List<RadioStationModelUI>? by radioStationsViewModel.stations.observeAsState()
    Log.i("GusMor", radioStationsViewModel.stations.value.toString())

    Box(
        Modifier
            .background(MaterialTheme.colorScheme.background)
           .fillMaxSize()
    ) {
        LazyColumn {
            items(
                stations.orEmpty(),
                key = { it.id }) { station ->
                if (station.enabled) ItemStation(
                    station,
                    navigationController,
                    playerViewModel
                )
            }
        }
    }
}

@Composable
fun ItemStation(
    station: RadioStationModelUI,
    navigationController: NavHostController,
    playerViewModel: PlayerViewModel
) {

    val coroutineScope = rememberCoroutineScope()
    val uri = Uri.parse(station.address.icy_url)

    Card(
        modifier = Modifier
            .shadow(4.dp)
            .testTag("ItemCard")
            .background(Color(0xFF1C1C1C))
            .padding(start = 16.dp, top = 8.dp)
            .clickable {
                coroutineScope.launch(Dispatchers.IO) {
                    playerViewModel.addStationModel(station)
                    playerViewModel.addMediaItem(uri)
                }
                navigationController.navigate(Routes.PlayerScreen.route)

            },
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(92.dp)
                .background(Color(0xFF1C1C1C)),

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        )
        {
            Row(
                Modifier
                    .height(92.dp)
                    .padding(4.dp)
                    .background(Color(0xFF1C1C1C)),

                verticalAlignment = Alignment.CenterVertically
            ) {
                StationCover(station.cover)
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(
                        text = station.name,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.testTag("TextItemTitle")
                    )
                    Spacer(modifier = Modifier.height(1.dp))
                    Text(
                        text = station.description ?: "cualquiera ",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.testTag("TextItemDescription")
                    )
                }
            }

            Box(
                Modifier
                    .padding(16.dp)
                    .size(32.dp)
                    .clickable { /*TODO*/ }
                    .background(Color(0xFF1C1C1C))
                    .testTag("MenuHorizontalItem")

            ) {
                val drawable =
                    AppCompatResources.getDrawable(LocalContext.current, R.drawable.more_horiz)
                Image(
                    painter = rememberDrawablePainter(drawable = drawable),
                    contentDescription = "MenuHorizImage",
                    colorFilter = ColorFilter.tint(Color(0xFF1C1C1C))
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
            .background(Color(0xFF1C1C1C))
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
                CircularProgressIndicator(
                    color = Color.Red, modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                )
            } else if (state is AsyncImagePainter.State.Error || state is AsyncImagePainter.State.Empty) {
                val drawable = AppCompatResources.getDrawable(LocalContext.current, R.drawable.blur)
                Image(
                    painter = rememberDrawablePainter(drawable = drawable),
                    modifier = Modifier.fillMaxSize(), contentDescription = "imagenBlur"
                )
            } else {
                SubcomposeAsyncImageContent()
            }
        }

    }
}
