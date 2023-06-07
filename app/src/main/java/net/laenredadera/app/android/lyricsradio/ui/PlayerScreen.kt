package net.laenredadera.app.android.lyricsradio.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import net.laenredadera.app.android.lyricsradio.R
import net.laenredadera.app.android.lyricsradio.Routes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PlayerScreen(navigationController: NavHostController, playerViewModel: PlayerViewModel) {

    Scaffold(
        topBar = {
            PlayerTopAppBar(navigationController)
        },
        content = {
            PlayerBody(playerViewModel)
        })
}

@Composable
fun PlayerBody(playerViewModel: PlayerViewModel) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(Modifier.weight(6f)) {
            Space(64)
            Image(
                painter = painterResource(id = R.drawable.blur),
                modifier = Modifier
                    .aspectRatio(1f)
                    .width(screenWidth / 2),
                contentScale = ContentScale.Fit,
                contentDescription = "albumCover",
            )

        }
        Column(Modifier.weight(4f)) {
            Space(16)
            Text(
                text = "Radio Name forever",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.testTag("StationNameInPlayer")
            )
            Space(4)
            Text(
                text = "Artist",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.testTag("ArtistNameInPlayer")
            )
            Text(
                text = "song name",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.testTag("SongTitleInPlayer")
            )

            Space(32)
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                IconButton(
                    modifier = Modifier.size(96.dp),
                    onClick = {
                       // playerViewModel.addMediaItem(uri)
                        playerViewModel.prepare()
                        playerViewModel.play() }) {

                    Image(
                        painter = painterResource(R.drawable.ic_play),
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = "playButton"
                    )
                }
            }
        }


    }
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
    TopAppBar(
        title = {
            Text(
                "En reproduccion",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = { navigationController.navigate(Routes.HomeScreen.route) }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Arrow Back to Home"
                )
            }
        },
        actions = {
            // RowScope here, so these icons will be placed horizontally

            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Localized description"
                )
            }
        },

        )

}