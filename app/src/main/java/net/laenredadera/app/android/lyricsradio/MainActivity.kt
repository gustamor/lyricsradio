package net.laenredadera.app.android.lyricsradio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import net.laenredadera.app.android.lyricsradio.ui.PlayerScreen
import net.laenredadera.app.android.lyricsradio.ui.PlayerViewModel
import net.laenredadera.app.android.lyricsradio.ui.RadioHomeScreen
import net.laenredadera.app.android.lyricsradio.ui.RadioStationViewModel
import net.laenredadera.app.android.lyricsradio.ui.theme.LyricsRadioTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val radioStationsViewModel: RadioStationViewModel by viewModels()
    private val playerViewModel: PlayerViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LyricsRadioTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PlayerScreen()
              //      RadioHomeScreen(radioStationsViewModel,playerViewModel)
                }
            }
        }
    }

}

