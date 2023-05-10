package net.laenredadera.app.android.lyricsradio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import net.laenredadera.app.android.lyricsradio.ui.RadioHomeScreen
import net.laenredadera.app.android.lyricsradio.ui.RadioStationViewModel
import net.laenredadera.app.android.lyricsradio.ui.theme.LyricsRadioTheme

class MainActivity : ComponentActivity() {

    private val radioStationsViewModel: RadioStationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LyricsRadioTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RadioHomeScreen(radioStationsViewModel)
                }
            }
        }
    }
}

