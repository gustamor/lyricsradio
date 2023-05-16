package net.laenredadera.app.android.lyricsradio.components

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import net.laenredadera.app.android.lyricsradio.ui.ItemStation
import net.laenredadera.app.android.lyricsradio.ui.StationCover
import org.junit.Rule
import org.junit.Test

class ComponentHomeScreenTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun ItemRadio_CheckIfExists() {
        composeTestRule.setContent {
            ItemStation()
        }
        composeTestRule.onNodeWithTag("ItemCard").assertExists()
    }
    @Test
    fun ItemRadio_CheckIfHasClickAction() {
        composeTestRule.setContent {
            ItemStation()
        }
        composeTestRule.onNodeWithTag("ItemCard").assertHasClickAction()
    }
    @Test
    fun StationConver_CheckIfExists() {
        composeTestRule.setContent {
            StationCover()
        }
        composeTestRule.onNodeWithTag("StationCover").assertExists()
        composeTestRule.onNodeWithContentDescription("stationCoverImage").assertExists()
    }
    @Test
    fun MenuHorizontal_CheckIfImageOfMenuExists() {
        composeTestRule.setContent {
            ItemStation()
        }
        composeTestRule.onNodeWithContentDescription("MenuHorizImage").assertExists()
    }
    @Test
    fun MenuHorizontal_CheckIfItemExists() {
        composeTestRule.setContent {
            ItemStation()
        }
        composeTestRule.onNodeWithTag("MenuHorizontalItem").assertExists()
    }
    @Test
    fun MenuHorizontal_CheckIfItemIsClickable() {
        composeTestRule.setContent {
            ItemStation()
        }
        composeTestRule.onNodeWithTag("MenuHorizontalItem").assertHasClickAction()
    }
}