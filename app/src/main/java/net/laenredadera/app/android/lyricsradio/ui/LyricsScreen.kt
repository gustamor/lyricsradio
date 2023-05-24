package net.laenredadera.app.android.lyricsradio.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import net.laenredadera.app.android.lyricsradio.ui.model.LyricsModel

@Composable
fun LyricsScreen(lyricsViewModel: LyricsViewModel) {
    lyricsViewModel.getLyrics("kreator", "outcast")

    val lyricModel: LyricsModel? by lyricsViewModel.lyrics.observeAsState()

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = Color.White)
    ) {
        if (lyricModel?.lyrics == null || lyricModel?.lyrics == "") {
            } else {
            Text(text = lyricModel!!.lyrics )
        }

    }

}
