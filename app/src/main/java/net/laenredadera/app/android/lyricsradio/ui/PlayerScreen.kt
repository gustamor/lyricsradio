package net.laenredadera.app.android.lyricsradio.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import net.laenredadera.app.android.lyricsradio.R
import net.laenredadera.app.android.lyricsradio.Routes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PlayerScreen(navigationController: NavHostController, playerViewModel: PlayerViewModel) {

    Box(
        modifier = Modifier.background(Color(0xFF1C1C1C)),
    )
    {
        PlayerTopAppBar(navigationController)
        PlayerBody(playerViewModel)
    }

}

@Composable
fun PlayerBody(playerViewModel: PlayerViewModel = hiltViewModel()) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val heightSize = screenWidth + 300.dp
    val station = playerViewModel.station.observeAsState()
    val playerStateFlow = playerViewModel.uiIsPlying.observeAsState(false)
    val song by playerViewModel.song.collectAsStateWithLifecycle()
    playerViewModel.albumCover()
    Column(
        modifier = Modifier
            .padding(top = 64.dp, start = 16.dp, end = 16.dp)
            .fillMaxSize()
            .background(Color(0xFF1C1C1C))
            .border(1.dp, Color.Red),
    ) {
        Box(Modifier.weight(1.4f)) {
            Space(64)
            SubcomposeAsyncImage(
                model = station.value?.cover ?: "",
                contentDescription = "albumCover",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(screenWidth)
                    .height(screenWidth)
                    .clip(RoundedCornerShape(32.dp))
            ) {
                val state = painter.state
                when (state) {
                    is AsyncImagePainter.State.Loading -> {
                        CircularProgressIndicator(
                            color = Color.Red,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp)
                                .clip(RoundedCornerShape(16.dp)),
                        )
                    }

                    is AsyncImagePainter.State.Error, is AsyncImagePainter.State.Empty -> {
                        val blur = AppCompatResources.getDrawable(
                            LocalContext.current,
                            R.drawable.blur
                        )
                        Image(
                            painter = rememberDrawablePainter(drawable = blur),
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(16.dp)),
                            contentDescription = "imagenBlur"
                        )
                    }

                    else -> {
                        SubcomposeAsyncImageContent()
                    }
                }
            }

        }

        Column(
            Modifier.weight(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Space(2)
            Text(
                text = station.value?.name ?: " Radio Name forever",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.testTag("StationNameInPlayer")
            )
            Space(1)
            Text(
                text = song[0] ?: "Radio name",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.testTag("ArtistNameInPlayer")
            )
            Text(
                text = song[1] ?: "radio name",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.testTag("SongTitleInPlayer")
            )

            Space(4)

            Row(
                horizontalArrangement = Arrangement.Center, modifier = Modifier
                    .fillMaxWidth()
                    .height(96.dp)
                    .border(1.dp, Color.Green)
            ) {
                IconButton(
                    modifier = Modifier
                        .size(112.dp)
                        .padding(bottom = 16.dp, top = 12.dp),
                    onClick = {
                        Log.i("GusMor", playerStateFlow.value.toString())

                        if (!playerStateFlow.value) {
                            playerViewModel.prepare()
                            playerViewModel.play().apply {
                                Log.i("GusMor1", playerViewModel.uiIsPlying.value.toString())
                            }
                        } else {
                            playerViewModel.stop().apply {
                                Log.i("GusMor2", playerViewModel.uiIsPlying.value.toString())
                            }

                        }
                    }) {
                    if (!playerStateFlow.value) {
                        val play = AppCompatResources.getDrawable(
                            LocalContext.current,
                            androidx.media3.ui.R.drawable.exo_icon_play
                        )
                        Image(
                            painter = rememberDrawablePainter(drawable = play),
                            modifier = Modifier.size(64.dp),
                            contentDescription = "playButton"
                        )
                    } else {
                        val pause = AppCompatResources.getDrawable(
                            LocalContext.current,
                            androidx.media3.ui.R.drawable.exo_icon_pause
                        )
                        Image(
                            painter = rememberDrawablePainter(drawable = pause),
                            contentDescription = "pauseButton"
                        )
                    }
                }
            }
            Row(
                Modifier
                    .height(48.dp)
                    .padding(horizontal = 4.dp)
                    .width(screenWidth),
                horizontalArrangement = Arrangement.Center
            ) {

                val play = AppCompatResources.getDrawable(
                    LocalContext.current,
                    androidx.media3.ui.R.drawable.exo_icon_play
                )
                val stop = AppCompatResources.getDrawable(
                    LocalContext.current,
                    androidx.media3.ui.R.drawable.exo_icon_play
                )
                Image(
                    painter = rememberDrawablePainter(drawable = play),
                    modifier = Modifier
                        .size(48.dp)
                        .padding(top = 16.dp, end = 8.dp)
                        .background(Color.Black),
                    contentDescription = "volumenStop"
                )
                 VolumeSlider( Modifier
                     .weight(1f)
                     .testTag("NowPlayingSlider"), playerViewModel)

                Image(
                    painter = rememberDrawablePainter(drawable = stop),
                    modifier = Modifier
                        .size(48.dp)
                        .padding(top = 16.dp, start = 8.dp)
                        .background(Color.Black),
                    contentDescription = "volumenMax"
                )
            }
            Box(
                Modifier
                    .height(56.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun VolumeSlider(modifier: Modifier, playerViewModel: PlayerViewModel) {
    var position = rememberSaveable { mutableStateOf(1f) }

    Slider(
        colors = SliderDefaults.colors(
            thumbColor = Color.Magenta,
            activeTrackColor = Color.Magenta,
            inactiveTrackColor = Color.DarkGray
        ),
        modifier = modifier,
        value = position.value,

        onValueChange = {
            position.value = it;
            playerViewModel.setVolume(position.value)
        },
        valueRange = 0f..1f,

        )
}

/**
 * Insert vertical space to the layout
 *
 * @param size in Int
 *
 */

@Composable
fun Space(size: Int) {
    Spacer(Modifier.size(size.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerTopAppBar(navigationController: NavHostController) {
    Row(modifier = Modifier
        .background(Color(0xFF1C1C1C))
        .testTag("NowPlayingHeaderRow"))

    {
        IconButton(onClick = { navigationController.navigate(Routes.MainScreen.route) }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Arrow Back to Main"
            )
        }
        /* Text(
             "En reproduccion",
             maxLines = 1,
             fontSize = 24.sp,
             fontWeight = FontWeight.Bold,
             modifier = Modifier.testTag("NowPlayingHeaderText")        )*/
    }


}