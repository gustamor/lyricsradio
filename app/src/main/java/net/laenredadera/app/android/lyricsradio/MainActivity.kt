package net.laenredadera.app.android.lyricsradio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


import dagger.hilt.android.AndroidEntryPoint
import net.laenredadera.app.android.lyricsradio.ui.MainScreen
import net.laenredadera.app.android.lyricsradio.ui.PlayerScreen
import net.laenredadera.app.android.lyricsradio.ui.PlayerViewModel
import net.laenredadera.app.android.lyricsradio.ui.RadioHomeScreen
import net.laenredadera.app.android.lyricsradio.ui.RadioStationViewModel
import net.laenredadera.app.android.lyricsradio.ui.theme.LyricsRadioTheme
import net.laenredadera.app.android.lyricsradio.ui.theme.PreviouslyPlayedScreen

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
                    color = Color(0xFF1C1C1C)
                ) {
                    val navigationController = rememberNavController()
                    NavHost(navController = navigationController, startDestination = Routes.MainScreen.route){
                        composable(Routes.HomeScreen.route) { RadioHomeScreen(navigationController,radioStationsViewModel,playerViewModel)}
                        composable(Routes.PlayerScreen.route) {PlayerScreen(navigationController,playerViewModel)}
                        composable(Routes.MainScreen.route) {MainScreen(navigationController,radioStationsViewModel,playerViewModel)}
                        composable(Routes.PreviouslyPlayedScreen.route) {PreviouslyPlayedScreen()}
                    }
                }
            }
        }
    }
}

