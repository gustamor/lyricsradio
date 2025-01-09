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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import net.laenredadera.app.android.lyricsradio.ui.model.RadioStationModelUI
import net.laenredadera.app.android.lyricsradio.ui.theme.LatoFont

@Composable
fun MainScreen(
    navigationController: NavHostController,
    radioStationsViewModel: RadioStationViewModel,
    playerViewModel: PlayerViewModel,

) {
    radioStationsViewModel.getStations()
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 4.dp)
            .background(Color(0xFF1C1C1C))
    ) {
        MainBody(radioStationsViewModel,navigationController,playerViewModel)
    }
}

@Composable
fun MainBody(
    radioStationsViewModel: RadioStationViewModel,
    nav: NavHostController,
    playerViewModel: PlayerViewModel,
) {
    val stations: List<RadioStationModelUI>? by radioStationsViewModel.stations.observeAsState()
    Log.i("GusMor", radioStationsViewModel.stations.value.toString())
    Column(
        Modifier.background(Color(0xFF1C1C1C)).padding(top = 21.dp)

    ) {
        SubHeaderMain("Welcome the destroyer", "Explore", nav)
        Space(18)
            LazyRow(
                Modifier
                    .fillMaxWidth()
                    .height(128.dp)) {
                items(stations.orEmpty(), key = { it.id }) { st ->
                    if (st.enabled)
                        RoundedBordersSquareImage(
                            station = st,
                            nav = nav,
                            playerViewModel = playerViewModel,
                            width = 176.dp,
                            height = 176.dp,
                            modifier = Modifier
                                .padding(horizontal = 6.dp)
                        )
                }
        }
        Space(40)
     //   SubHeaderMain("Favs", "View All", nav)
        Row(Modifier.height(160.dp)) {
            RoundedBordersRectangleImage(
                painter = painterResource(id = R.drawable.mostplayedstations),
                headText = "Your top",
                bodyText = "Stations",
                width = 450.dp,
                height = 384.dp,
                contentDescription = "topstationsgotoButton",
                modifier = Modifier
                    .padding(horizontal = 6.dp),
                clickFunction = { nav.navigate(Routes.TopStationsScreen.route) }
            )
            /*RoundedBordersRectangleImage(
                painter = painterResource(id = R.drawable.mostplayed2),
                width = 200.dp,
                height = 128.dp,
                headText = "Recently",
                bodyText = "Played",
                contentDescription = "lastplayedsongs",
                modifier = Modifier
                    .padding(horizontal = 6.dp),
                clickFunction =  { nav.navigate(Routes.PreviouslyPlayedScreen.route) },
            )*/
        }
        Space(4)
      //  TitleHeaderMain("Now Playing", 24)
        Space(4)
        Column(Modifier.verticalScroll(rememberScrollState())) {

        }
    }
}

@Composable
fun TitleHeaderMain(title: String, fontSize: Int) {
    Text(
        text = title, fontSize = fontSize.sp,
        color = Color.LightGray,
        fontWeight = FontWeight.Bold, modifier = Modifier
            .padding(8.dp)
            .testTag(title)
    )
}

@Composable
fun SubHeaderMain(title: String, textButton: String?, nav: NavHostController) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        TitleHeaderMain(title, 21)
        if (textButton != null) {
            Button(
                onClick = { nav.navigate(Routes.HomeScreen.route) },
                colors = ButtonDefaults.buttonColors(Color.Magenta),
                shape = RoundedCornerShape(32.dp),
                modifier = Modifier
                    .height(32.dp)
                    .padding(end = 16.dp),
            ) {
                Text(text = textButton)
            }
        }
    }
}

@Composable
fun RoundedBordersSquareImage(
    station: RadioStationModelUI,
    nav: NavHostController,
    playerViewModel: PlayerViewModel,
    width: Dp,
    height: Dp,
    modifier: Modifier = Modifier,
) {
    val cover = Uri.parse(station.cover)
    val uri = Uri.parse(station.address.icy_url)
    val coroutineScope = rememberCoroutineScope()
Box() {
    SubcomposeAsyncImage(
        model = cover,
        contentDescription = "stationCoverImage",
        contentScale = ContentScale.FillBounds,
        modifier = modifier
            .aspectRatio(1f)
            .height(height)
            .width(width)
            .clip(RoundedCornerShape(32.dp))
            .clickable {
                coroutineScope.launch() {
                    withContext(Dispatchers.IO) {
                        playerViewModel.addStationModel(station)
                        playerViewModel.addMediaItem(uri)
                    }
                    nav.navigate(Routes.PlayerScreen.route)
                }
            }
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator(color = Color.Red, modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp))
            }
            is AsyncImagePainter.State.Error, is AsyncImagePainter.State.Empty -> {
                val drawable = AppCompatResources.getDrawable(LocalContext.current, R.drawable.blur)
                Image(painter = rememberDrawablePainter(drawable = drawable),
                    modifier = Modifier.fillMaxSize(), contentDescription = "imagenBlur")
            }
            else -> {
                SubcomposeAsyncImageContent()
            }
        }
    }
}
}

@Composable
fun RoundedBordersRectangleImage(
    painter: Painter,
    headText: String,
    bodyText: String,
    width: Dp,
    height: Dp,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    clickFunction: () -> Unit ,
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val w = (((screenWidth)-(screenWidth/10))/2f)

    Box(contentAlignment=Alignment.Center, modifier= Modifier.clickable {  clickFunction() }) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.FillBounds,
                modifier = modifier
                    .height(height)
                    .width(width)
                    .width(w.dp)
                    .clip(RoundedCornerShape(32.dp))
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(headText, color= Color.White, style = MaterialTheme.typography.titleLarge)
                Text(bodyText, color= Color.White, style = MaterialTheme.typography.titleMedium)
            }
    }
}

@Composable
fun ItemNowPlaying() {
    Box(
        modifier = Modifier
            .shadow(4.dp)
            .testTag("ItemCard")
            .background(Color(0xFF1C1C1C))
            .clickable { /*TODO */ }
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
                val blur = AppCompatResources.getDrawable(LocalContext.current, R.drawable.blur)
                Image(
                    painter = rememberDrawablePainter(drawable = blur),
                    contentDescription = "nowPlayingCoverImage",
                    modifier = Modifier
                        .height(40.dp)
                        .width(40.dp)
                )
                Column(Modifier.padding(start = 8.dp)) {
                    Text(
                        text = "Artist name",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.testTag("TextItemNowplayingArtist")
                    )
                    Spacer(modifier = Modifier.height(1.dp))
                    Text(
                        text = "Song title ",
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.testTag("TextItemNowplayingSong")
                    )
                }
            }
            Box(
                Modifier
                    .padding(16.dp)
                    .size(32.dp)
                    .clickable { /*TODO*/ }
                    .testTag("MenuHorizontalItem")
            ) {
                val drawable = AppCompatResources.getDrawable(
                    LocalContext.current,
                    androidx.media3.ui.R.drawable.exo_icon_play
                )
                Icon(
                    painter = rememberDrawablePainter(drawable = drawable),
                    tint = Color.White,
                    contentDescription = "MenuHorizImage",
                )
            }
        }
    }
}
