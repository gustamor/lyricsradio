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
import androidx.compose.foundation.layout.padding
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
import net.laenredadera.app.android.lyricsradio.ui.MainScreen
import net.laenredadera.app.android.lyricsradio.ui.PlayerScreen
import net.laenredadera.app.android.lyricsradio.ui.PlayerViewModel
import net.laenredadera.app.android.lyricsradio.ui.ExploreStationScreen
import net.laenredadera.app.android.lyricsradio.ui.PlayerControls
import net.laenredadera.app.android.lyricsradio.ui.TopStationsScreen
import net.laenredadera.app.android.lyricsradio.ui.theme.LyricsRadioTheme
import net.laenredadera.app.android.lyricsradio.ui.vm.RadioStationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val radioStationsViewModel: RadioStationViewModel by viewModels()
    private val playerViewModel: PlayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LyricsRadioTheme {
                val scaffoldState = rememberBottomSheetScaffoldState()

                BottomSheetScaffold(
                    scaffoldState = scaffoldState,
                    sheetContent = {
                        // Mini reproductor que siempre está visible
                        PlayerControls(playerViewModel)
                    },
                    sheetPeekHeight = 80.dp
                ) { paddingValues ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        color = Color(0xFF1C1C1C)
                    ) {
                        val navigationController = rememberNavController()

                        NavHost(
                            navController = navigationController,
                            startDestination = Routes.MainScreen.route
                        ) {
                            composable(Routes.MainScreen.route) {
                                MainScreen(
                                    navigationController = navigationController,
                                    radioStationsViewModel = radioStationsViewModel,
                                    playerViewModel = playerViewModel
                                )
                            }

                            composable(Routes.HomeScreen.route) {
                                ExploreStationScreen(
                                    navigationController = navigationController,
                                    radioStationsViewModel = radioStationsViewModel,
                                    playerViewModel = playerViewModel
                                )
                            }

                            composable(Routes.PlayerScreen.route) {
                                PlayerScreen(
                                    navigationController = navigationController,
                                    playerViewModel = playerViewModel
                                )
                            }

                            composable(Routes.TopStationsScreen.route) {
                                TopStationsScreen(
                                    navigationController = navigationController,
                                    playerViewModel = playerViewModel
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


