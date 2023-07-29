package net.laenredadera.app.android.lyricsradio


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import net.laenredadera.app.android.lyricsradio.ui.Botonera
import net.laenredadera.app.android.lyricsradio.ui.MainScreen
import net.laenredadera.app.android.lyricsradio.ui.PlayerScreen
import net.laenredadera.app.android.lyricsradio.ui.PlayerViewModel
import net.laenredadera.app.android.lyricsradio.ui.ExploreStationScreen
import net.laenredadera.app.android.lyricsradio.ui.RadioStationViewModel
import net.laenredadera.app.android.lyricsradio.ui.TopStationsScreen
import net.laenredadera.app.android.lyricsradio.ui.theme.LyricsRadioTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val radioStationsViewModel: RadioStationViewModel by viewModels()
    private val playerViewModel: PlayerViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LyricsRadioTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentColor = Color(0xFF1C1C1C),
                    color = Color(0xFF1C1C1C),

                    ) {
                    val station = playerViewModel.station.observeAsState()
                    val playerStateFlow = playerViewModel.uiIsPlaying.collectAsStateWithLifecycle()
                    val sheetPeekHeight = if (playerStateFlow.value) 24.dp else 0.dp
                    BottomSheetScaffold(
                        scaffoldState = rememberBottomSheetScaffoldState(),
                        sheetPeekHeight = sheetPeekHeight,
                        sheetTonalElevation = 0.dp,
                        sheetShadowElevation = 0.dp,
                        sheetDragHandle = {
                            if (station.value != null) {
                                if (!playerStateFlow.value) {
                                    Text(" ")
                                } else {
                                    if (station.value!!.name == " ") Text(" ") else Text(station.value!!.name)
                                }
                            }
                        },
                        contentColor = Color(0xFF1C1C1C),
                        sheetContentColor = Color(0xFF1C1C1C),
                        containerColor = Color(0xFF1C1C1C),
                        sheetContent = {
                            if (playerStateFlow.value) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color(0xFF1C1C1C))
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier
                                            .verticalScroll(rememberScrollState())
                                            .background(Color(0xFF1C1C1C))
                                    ) {
                                        Botonera()
                                    }
                                }
                            }
                        }
                    ) {
                        val navigationController: NavHostController = rememberNavController()
                        NavHost(
                            navController = navigationController,
                            startDestination = Routes.MainScreen.route,
                        ) {
                            composable(Routes.HomeScreen.route) {
                                ExploreStationScreen(
                                    navigationController,
                                    radioStationsViewModel,
                                    playerViewModel
                                )
                            }
                            composable(Routes.PlayerScreen.route) {
                                PlayerScreen(
                                    navigationController,
                                    playerViewModel
                                )
                            }
                            composable(Routes.MainScreen.route) {
                                MainScreen(
                                    navigationController,
                                    radioStationsViewModel, playerViewModel
                                )
                            }
                            composable(Routes.TopStationsScreen.route) {
                                TopStationsScreen(
                                    navigationController,
                                    playerViewModel
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


