package net.laenredadera.app.android.lyricsradio.components

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.laenredadera.app.android.lyricsradio.ui.ItemStation
import net.laenredadera.app.android.lyricsradio.ui.PlayerViewModel
import net.laenredadera.app.android.lyricsradio.ui.StationCover
import net.laenredadera.app.android.lyricsradio.ui.model.RadioStationModelUI
import net.laenredadera.app.android.lyricsradio.ui.model.RadioStationsAddressUI
import org.junit.Rule
import org.junit.Test

class ComponentHomeScreenTest {
    private val station: RadioStationModelUI = RadioStationModelUI(
        1,
        true,
        "Radio Name",
        "https://www.easylinedrawing.com/wp-content/uploads/2021/07/log_drawing.png",
        "urlradio",
        "",
        RadioStationsAddressUI("", "", "", "")
    )
    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun ItemRadio_CheckIfExists() {
        composeTestRule.setContent {
            val navHostController: NavHostController = rememberNavController()
            val playerViewModel : PlayerViewModel = hiltViewModel()
            ItemStation(station,navHostController, playerViewModel)
        }
        composeTestRule.onNodeWithTag("ItemCard").assertExists()
    }

    @Test
    fun ItemRadio_CheckIfHasClickAction() {
        composeTestRule.setContent {
            val navHostController: NavHostController = rememberNavController()
            val playerViewModel : PlayerViewModel = hiltViewModel()
            ItemStation(station,navHostController, playerViewModel)
        }
        composeTestRule.onNodeWithTag("ItemCard").assertHasClickAction()
    }

    @Test
    fun StationCoverRemoteImage_CheckIfExists() {
        val cover = "https://www.easylinedrawing.com/wp-content/uploads/2021/07/log_drawing.png"
        composeTestRule.setContent {
            StationCover(cover)
        }
        composeTestRule.onNodeWithTag("StationCover").assertExists()
        composeTestRule.onNodeWithContentDescription("stationCoverImage").assertExists()
    }
    @Test
    fun StationCoverComponent_CheckIfExists() {
        val cover = ""
        composeTestRule.setContent {
            StationCover(cover)
        }
        composeTestRule.onNodeWithTag("StationCover").assertExists()
    }
    @Test
    fun StationCoverRemoteImage_CheckIfNotExists() {
        val cover = "https://www.easylinedrawing.com/wp-content/uploads/2021/07/log_drawingqg"
        composeTestRule.setContent {
            StationCover(cover)
        }
        composeTestRule.onNodeWithContentDescription("stationCoverImage").assertDoesNotExist()
    }
    @Test
    fun ItemText_CheckIfExistsTextOfItemTitle() {
        composeTestRule.setContent {
            val navHostController: NavHostController = rememberNavController()
            val playerViewModel : PlayerViewModel = hiltViewModel()
            ItemStation(station,navHostController, playerViewModel)
        }
        composeTestRule.onNodeWithTag("TextItemTitle").assertExists()
    }

    @Test
    fun ItemText_CheckIfExistsTextOfItemDescription() {
        composeTestRule.setContent {
            val navHostController: NavHostController = rememberNavController()
            val playerViewModel : PlayerViewModel = hiltViewModel()
            ItemStation(station,navHostController, playerViewModel)
        }
        composeTestRule.onNodeWithTag("TextItemDescription").assertExists()
    }

    @Test
    fun MenuHorizontal_CheckIfImageOfMenuExists() {
        composeTestRule.setContent {
            val navHostController: NavHostController = rememberNavController()
            val playerViewModel : PlayerViewModel = hiltViewModel()
            ItemStation(station,navHostController, playerViewModel)
        }
        composeTestRule.onNodeWithContentDescription("MenuHorizImage").assertExists()
    }

    @Test
    fun MenuHorizontal_CheckIfItemExists() {
        composeTestRule.setContent {
            val navHostController: NavHostController = rememberNavController()
            val playerViewModel : PlayerViewModel = hiltViewModel()
            ItemStation(station,navHostController, playerViewModel)
        }
        composeTestRule.onNodeWithTag("MenuHorizontalItem").assertExists()
    }

    @Test
    fun MenuHorizontal_CheckIfItemIsClickable() {
        composeTestRule.setContent {
            val navHostController: NavHostController = rememberNavController()
            val playerViewModel : PlayerViewModel = hiltViewModel()
            ItemStation(station,navHostController, playerViewModel)
        }
        composeTestRule.onNodeWithTag("MenuHorizontalItem").assertHasClickAction()
    }
}
