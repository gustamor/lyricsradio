package net.laenredadera.app.android.lyricsradio.ui

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.laenredadera.app.android.lyricsradio.R
import net.laenredadera.app.android.lyricsradio.Routes


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PlayerScreen(navigationController: NavHostController, playerViewModel: PlayerViewModel) {

    Column(
        modifier = Modifier.background(Color(0xFF1C1C1C)),
    ) {
        PlayerTopAppBar(navigationController,playerViewModel)
        PlayerBody(playerViewModel)
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun PlayerBody(playerViewModel: PlayerViewModel = hiltViewModel()) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val station = playerViewModel.station.observeAsState()
    val playerStateFlow = playerViewModel.uiIsPlaying.collectAsStateWithLifecycle()
    val song by playerViewModel.song.collectAsStateWithLifecycle()
    val albumCover by playerViewModel.cover.observeAsState()
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(song) {
        playerViewModel.albumCover()
    }
    Column(
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxSize()
            .background(Color(0xFF1C1C1C))
            .border(1.dp, Color.Red),
    ) {
        Box(Modifier.weight(1.2f)) {
            Space(64)
            SubcomposeAsyncImage(
                model = if (albumCover == "") station.value?.cover else albumCover,
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
                            LocalContext.current, R.drawable.blur
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
        ) {

            Space(16)
            Text(
                text = song[0],
                color = Color.White,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.testTag("ArtistNameInPlayer")
            )
            Text(
                text = song[1],
                color = Color.White,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.testTag("SongTitleInPlayer")
            )
            Space(16)
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(96.dp)
                    .border(1.dp, Color.Green)
            ) {
                IconButton(modifier = Modifier
                    .size(112.dp)
                    .padding(bottom = 16.dp, top = 12.dp),
                    onClick = {
                        if ( !playerStateFlow.value) {
                            coroutineScope.launch {
                                withContext(Dispatchers.IO) {
                                    playerViewModel.play().apply {
                                        playerViewModel.albumCover()
                                    }
                                }
                            }
                        } else {
                            coroutineScope.launch {
                                withContext(Dispatchers.IO) {
                                    playerViewModel.stop()
                                }
                            }
                        }
                    }) {
                    if (!playerStateFlow.value) {
                        val play = AppCompatResources.getDrawable(
                            LocalContext.current, R.drawable.ic_play
                        )
                        Icon(
                            painter = rememberDrawablePainter(drawable = play),
                            tint = Color.White,
                            modifier = Modifier.size(56.dp),
                            contentDescription = "playButton"
                        )
                    } else {
                        val pause = AppCompatResources.getDrawable(
                            LocalContext.current, R.drawable.ic_pause
                        )
                        Icon(
                            painter = rememberDrawablePainter(drawable = pause),
                            tint = Color.White,
                            modifier = Modifier.size(56.dp),
                            contentDescription = "pauseButton"
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
            ) { }
        }
    }
}

@Composable
fun VolumeSlider(modifier: Modifier, playerViewModel: PlayerViewModel) {
    val position = rememberSaveable { mutableStateOf(1f) }

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
fun PlayerTopAppBar(navigationController: NavHostController, playerViewModel: PlayerViewModel = hiltViewModel()) {
    val station = playerViewModel.station.observeAsState()

    Row(
        modifier = Modifier
            .background(Color(0xFF1C1C1C))
            .testTag("NowPlayingHeaderRow")
    )

    {
        IconButton(onClick = { navigationController.navigate(Routes.MainScreen.route) }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                tint = Color.White,
                contentDescription = "Arrow Back to Main"
            )
        }
        Text(
            text = station.value?.name ?: " Radio Station",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top=12.dp, start=32.dp). testTag("StationNameInTabBar")
        )
    }
}

@SuppressLint("PrivateResource")
@Composable
fun Botonera(playerViewModel: PlayerViewModel = hiltViewModel()) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val station = playerViewModel.station.observeAsState()
    val playerStateFlow = playerViewModel.uiIsPlaying.collectAsStateWithLifecycle()
    val playerStatePauseFlow = playerViewModel.uiIsPaused.collectAsStateWithLifecycle()
    val song by playerViewModel.song.collectAsStateWithLifecycle()
    val albumCover by playerViewModel.cover.observeAsState()
    val coroutineScope = rememberCoroutineScope()
    val volume = playerViewModel.volume.collectAsStateWithLifecycle(initialValue = 1f)
    val playDrawable = AppCompatResources.getDrawable(
        LocalContext.current,
        androidx.media3.ui.R.drawable.exo_icon_play
    )
    val stopDrawable = AppCompatResources.getDrawable(
        LocalContext.current,
        androidx.media3.ui.R.drawable.exo_icon_pause
    )

    Box(
        modifier = Modifier
            .shadow(4.dp)
            .testTag("Botonera")
            .background(Color(0xFF1C1C1C))
            .height(132.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(92.dp)
                    .padding(8.dp)
                    .background(Color(0xFF1C1C1C)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            )
            {
                Row(
                    Modifier
                        .height(92.dp)
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SubcomposeAsyncImage(
                        model = if (!playerStateFlow.value) station.value?.cover
                                else if (albumCover == "") station.value?.cover
                                     else albumCover,
                        contentDescription = "nowPlayingCoverImageBotonera",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .height(48.dp)
                            .width(48.dp)
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
                                    LocalContext.current, R.drawable.blur
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
                    Column(Modifier.padding(start = 8.dp)) {
                        Text(
                            text = song[0],
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.testTag("TextItemNowplayingArtistBotonera")
                        )
                        Spacer(modifier = Modifier.height(1.dp))
                        Text(
                            text = song[1],
                            fontSize = 16.sp,
                            color = Color.White,
                            modifier = Modifier.testTag("TextItemNowplayingSongBotonera")
                        )
                    }
                }
                Box(
                    Modifier
                        .padding(4.dp)
                        .size(48.dp)
                        .clickable {
                            if (!playerStatePauseFlow.value) {
                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {
                                        playerViewModel.stop()
                                    }
                                }
                            } else {
                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {
                                        playerViewModel.play()
                                    }
                                }
                            }
                        }
                        .testTag("PlayStopBotonera")
                ) {
                     if (!playerStatePauseFlow.value ) {
                         Icon(
                             painter = rememberDrawablePainter(drawable = stopDrawable!!),
                             tint = Color.White,
                             contentDescription = "PlayStopImageBotonera",
                         )

                    } else { Icon(
                         painter = rememberDrawablePainter(drawable =  playDrawable!!),
                         tint = Color.White,
                         contentDescription = "PlayStopImageBotonera",
                     )

                    }

                }
            }
            Row(
                Modifier
                    .height(32.dp)
                    .padding(horizontal = 4.dp)
                    .width(screenWidth),
                horizontalArrangement = Arrangement.Center
            ) {
                val volumeMute = AppCompatResources.getDrawable(
                    LocalContext.current, R.drawable.ic_volume_mute
                )
                val volumeDown = AppCompatResources.getDrawable(
                    LocalContext.current, R.drawable.ic_volume_down
                )
                val volumeUp = AppCompatResources.getDrawable(
                    LocalContext.current, R.drawable.ic_volume_up
                )
                if (volume.value < 0.01) {
                    Icon(
                        painter = rememberDrawablePainter(drawable = volumeMute),
                        tint = Color.White,
                        modifier = Modifier
                            .size(24.dp)
                            .padding(top = 8.dp, end = 4.dp),
                        contentDescription = "volumeDown"
                    )
                } else {
                    Icon(
                        painter = rememberDrawablePainter(drawable = volumeDown),
                        tint = Color.White,
                        modifier = Modifier
                            .size(24.dp)
                            .padding(top = 8.dp, end = 4.dp),
                        contentDescription = "volumeDown"
                    )
                }
                VolumeSlider(
                    Modifier
                        .weight(1f)
                        .testTag("NowPlayingSlider"), playerViewModel
                )
                Icon(
                    painter = rememberDrawablePainter(drawable = volumeUp),
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(top = 8.dp, start = 4.dp),
                    contentDescription = "volumeUp"
                )
            }
            Space(size = 4)
        }
    }
}



