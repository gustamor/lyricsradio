package net.laenredadera.app.android.lyricsradio.components

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import net.laenredadera.app.android.lyricsradio.data.network.model.RadioStationsAddress
import net.laenredadera.app.android.lyricsradio.ui.ItemStation
import net.laenredadera.app.android.lyricsradio.ui.StationCover
import net.laenredadera.app.android.lyricsradio.ui.model.RadioStationModel
import org.junit.Rule
import org.junit.Test

class ComponentHomeScreenTest {
    val station: RadioStationModel = RadioStationModel(
        1,
        true,
        "Radio Name",
        "https://www.easylinedrawing.com/wp-content/uploads/2021/07/log_drawing.png",
        "urlradio",
        "",
        RadioStationsAddress("", "", "", "")
    )

    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun ItemRadio_CheckIfExists() {
        composeTestRule.setContent {
            ItemStation(station)
        }
        composeTestRule.onNodeWithTag("ItemCard").assertExists()
    }

    @Test
    fun ItemRadio_CheckIfHasClickAction() {
        composeTestRule.setContent {
            ItemStation(station)
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
            ItemStation(station)
        }
        composeTestRule.onNodeWithTag("TextItemTitle").assertExists()
    }

    @Test
    fun ItemText_CheckIfExistsTextOfItemDescription() {
        composeTestRule.setContent {
            ItemStation(station)
        }
        composeTestRule.onNodeWithTag("TextItemDescription").assertExists()
    }

    @Test
    fun MenuHorizontal_CheckIfImageOfMenuExists() {
        composeTestRule.setContent {
            ItemStation(station)
        }
        composeTestRule.onNodeWithContentDescription("MenuHorizImage").assertExists()
    }

    @Test
    fun MenuHorizontal_CheckIfItemExists() {
        composeTestRule.setContent {
            ItemStation(station)
        }
        composeTestRule.onNodeWithTag("MenuHorizontalItem").assertExists()
    }

    @Test
    fun MenuHorizontal_CheckIfItemIsClickable() {
        composeTestRule.setContent {
            ItemStation(station)
        }
        composeTestRule.onNodeWithTag("MenuHorizontalItem").assertHasClickAction()
    }
}
