package net.laenredadera.app.android.lyricsradio.ui.theme

import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import net.laenredadera.app.android.lyricsradio.R
import net.laenredadera.app.android.lyricsradio.ui.PreviousPlayedSongViewModel
import net.laenredadera.app.android.lyricsradio.ui.Space
import net.laenredadera.app.android.lyricsradio.ui.TopStationsViewModel

@Composable
fun PreviouslyPlayedScreen(navigationController: NavHostController) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 32.dp)
            .verticalScroll(rememberScrollState())
            .background(Color(0xFF1C1C1C))
    ) {
        PlayedHeader(navigationController)
        PlayedBody(navigationController)
    }
}

@Composable
fun PlayedHeader(navigationController: NavHostController) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(80.dp),
        horizontalArrangement = Arrangement.SpaceBetween

    ) {

        IconButton(onClick = { navigationController.popBackStack() }) {
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
                contentDescription = "BackArrowHeaderIcon"
            )
        }
        Text(
            text = "Played",
            fontWeight = FontWeight.Bold,
            fontSize = 21.sp,
            color = Color.White,
            modifier = Modifier.testTag("PlayedHeaderText")
        )
        IconButton(onClick = { /*TODO*/ }) {
            val settings = AppCompatResources.getDrawable(
                LocalContext.current,
                androidx.media3.ui.R.drawable.exo_ic_settings
            )
            Image(
                painter = rememberDrawablePainter(drawable = settings),
                contentDescription = "PlayedHeaderIcon"
            )
        }
    }
}

@Composable
fun PlayedBody(navigationController: NavHostController) {

    Text(
        text = "Previously Played",
        fontSize = 21.sp,
        color = Color.White,
        modifier = Modifier
            .padding(start = 16.dp)
            .testTag("PlayedBodyText")
    )
    Space(16)
    PlayedItem()
    PlayedItem()
    PlayedItem()
    PlayedItem()
    PlayedItem()
}

@Composable
fun PlayedItem() {

    Column(
        modifier = Modifier
            .height(182.dp)
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(8f)
        ) {

            Image(
                painter = painterResource(id = R.drawable.blur),
                contentDescription = "coverOfTheAlbum",
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 16.dp)
                    .height(96.dp)
                    .width(96.dp)
                    .clip(
                        RoundedCornerShape(16.dp)
                    )
            )
            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                Text(
                    text = "Header Header",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.testTag("ListenedItemHeaderText")
                )
                Text(
                    text = "Sub header",
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.testTag("ListenedItemBodyText")
                )
                Space(12)
                Text(
                    text = "18 Ago 23",
                    fontSize = 14.sp,
                    color = Color.White,
                    modifier = Modifier.testTag("ListenedItemBodyText")
                )
            }
        }
        Row(
            Modifier
                .weight(2f)
                .padding(start = 32.dp)
        ) {
            Text(
                text = "Listened at Radio Metal Station",
                fontSize = 13.sp,
                color = Color.White,
                modifier = Modifier.testTag("RadioStationListenedAttext")
            )
        }
    }
}
