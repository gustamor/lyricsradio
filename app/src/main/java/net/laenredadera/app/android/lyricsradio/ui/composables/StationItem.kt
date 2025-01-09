package net.laenredadera.app.android.lyricsradio.ui.composables

import android.net.Uri
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.laenredadera.app.android.lyricsradio.R
import net.laenredadera.app.android.lyricsradio.Routes
import net.laenredadera.app.android.lyricsradio.ui.vm.PlayerViewModel
import net.laenredadera.app.android.lyricsradio.ui.model.RadioStationModelUI


@Composable
fun StationItem(station: RadioStationModelUI, navigationController: NavHostController, playerViewModel: PlayerViewModel) {

    val coroutineScope = rememberCoroutineScope()

    val uri = Uri.parse(station.address.icy_url)

    IconButton(onClick = {}) {
        Card(
            modifier = Modifier
                .shadow(4.dp)
                .testTag("ItemCard")
                .background(MaterialTheme.colorScheme.background)
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
                    Column {
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
                    val drawable = AppCompatResources.getDrawable(LocalContext.current, R.drawable.more_horiz)
                    Image(
                        painter = rememberDrawablePainter(drawable = drawable),
                        contentDescription = "MenuHorizImage",
                        colorFilter =  ColorFilter.tint(MaterialTheme.colorScheme.tertiary)
                    )
                }
            }
        }
    }
}
