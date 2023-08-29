package net.laenredadera.app.android.lyricsradio.ui

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import kotlinx.coroutines.withContext
import net.laenredadera.app.android.lyricsradio.R
import net.laenredadera.app.android.lyricsradio.Routes
import net.laenredadera.app.android.lyricsradio.ui.model.TopStationUi
import net.laenredadera.app.android.lyricsradio.ui.model.toRadioStation

@Composable
fun TopStationsScreen(navigationController: NavHostController, playerViewModel: PlayerViewModel) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 21.dp)
            .background(Color(0xFF1C1C1C))
    ) {
        TopStationsHeader(navigationController)
        TopStationsBody(navigationController, playerViewModel)
    }
}

@SuppressLint("PrivateResource")
@Composable
fun TopStationsHeader(
    nav: NavHostController,
    topStationViewModel: TopStationsViewModel = hiltViewModel()
) {
    var expanded by remember { mutableStateOf(false) }
    var dialogOpen by remember {    mutableStateOf(false)
    }
    Row(
        Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = { nav.popBackStack() }) {
            val backArrow = AppCompatResources.getDrawable(
                LocalContext.current,
                R.drawable.ic_back_arrow
            )
            Icon(
                painter = rememberDrawablePainter(drawable = backArrow),
                tint = Color.White,
                modifier = Modifier
                    .height(21.dp)
                    .width(21.dp),
                contentDescription = "BackArrowHeaderIconTopStations"
            )
        }
        Text(
            text = "Top Stations",
            fontWeight = FontWeight.Bold,
            fontSize = 21.sp,
            color = Color.White,
            modifier = Modifier.testTag("TopStationsHeaderText")
        )

        Box(
            contentAlignment = Alignment.TopStart
        ) {
            IconButton(
                onClick = {
                    expanded = !expanded
                },
                modifier = Modifier.testTag("TopStationsDropdownMenuIcon"),
            ) {
                val settings = AppCompatResources.getDrawable(
                    LocalContext.current,
                    R.drawable.ic_menu_other
                )
                Icon(
                    painter = rememberDrawablePainter(drawable = settings),
                    contentDescription = "PlayedHeaderIcon",
                    tint = Color.White,
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.testTag("TopStationsDropdownMenu"),

                ) {
                DropdownMenuItem(
                    text = { Text("Delete All") },
                    onClick = {
                        dialogOpen = true
                    },
                    modifier = Modifier.testTag("TopStationsDropdownMenuItem"),
                )
            }
        }
    }

    if (dialogOpen) {
        AlertDialog(

            onDismissRequest = {
                dialogOpen = false
                expanded = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        topStationViewModel.deleteTopStations()
                        dialogOpen = false
                        expanded = false
                    }
                ) {
                    Text(
                        text = "OK",
                        fontSize = 15.sp,
                        modifier = Modifier.testTag("TopStationsConfirmButtonInDialog")
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        dialogOpen = false
                        expanded = false
                    }
                ) {
                    Text(
                        text = "NO",
                        fontSize = 15.sp,
                        modifier = Modifier.testTag("TopStationsCancelButtonInDialog")
                    )
                }
            },
            title = {
                Text(
                    text = "Delete favorites",
                    fontSize = 21.sp,
                    modifier = Modifier.testTag("TopStationsTitleTextInDialog")
                )
            },
            text = {
                Text(
                    text = "Do you want to delete the of your most listened stations?",
                    fontSize = 18.sp,
                    modifier = Modifier.testTag("TopStationsDescriptionTextInDialog")
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
                .testTag("TopStationsAlertDialog"),
            shape = RoundedCornerShape(5.dp),
        )
    }
}

@Composable
fun TopStationsBody(nav: NavHostController, playerViewModel: PlayerViewModel) {
    val topStationViewModel: TopStationsViewModel = hiltViewModel()
    topStationViewModel.topStations()
    val topStations = topStationViewModel.topStations.collectAsState(emptyList())

    Text(
        text = "Your top stations",
        fontSize = 21.sp,
        color = Color.White,
        modifier = Modifier
            .padding(start = 16.dp)
            .testTag("TopStationsBodyHead")
    )
    Space(16)
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(topStations.value) { station ->
                TopStationItem(station!!, nav, playerViewModel)
            }
        }
        Space(64)
    }

}

@Composable
fun TopStationItem(
    item: TopStationUi,
    nav: NavHostController,
    playerViewModel: PlayerViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val uri = Uri.parse(item.address)

    Column(
        modifier = Modifier
            .height(128.dp)
            .fillMaxWidth()
            .padding(bottom = 8.dp, end = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(8f)
                .clickable {
                    coroutineScope.launch() {
                        withContext(Dispatchers.IO) {
                            playerViewModel.addStationModel(item.toRadioStation())
                            playerViewModel.addMediaItem(uri)
                        }
                        nav.navigate(Routes.PlayerScreen.route)
                    }
                }
        ) {
            SubcomposeAsyncImage(
                model = item.cover,
                contentDescription = "stationCover",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 16.dp)
                    .height(96.dp)
                    .width(96.dp)
                    .clip(
                        RoundedCornerShape(16.dp)
                    )
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
                            contentDescription = "imagenBlurTopStation"
                        )
                    }

                    else -> {
                        SubcomposeAsyncImageContent()
                    }
                }
            }

            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                Text(
                    text = item.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.testTag("TopStationItemNameText")
                )
                Space(4)
                Text(
                    text = "Times played: ${item.numTimesPlayed}",
                    fontSize = 15.sp,
                    color = Color.White,
                    modifier = Modifier.testTag("TopStationItemDescriptionText")
                )
                Space(12)
            }
        }
    }
}