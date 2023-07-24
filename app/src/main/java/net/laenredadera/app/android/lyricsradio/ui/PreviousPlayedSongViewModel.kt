package net.laenredadera.app.android.lyricsradio.ui

import android.view.View
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import net.laenredadera.app.android.lyricsradio.domain.GetPlayedTracksUseCase
import net.laenredadera.app.android.lyricsradio.domain.model.PlayedTrackDataModel
import net.laenredadera.app.android.lyricsradio.domain.model.toDataUI
import net.laenredadera.app.android.lyricsradio.ui.model.PlayedTrackModelUI
import javax.inject.Inject

class PreviousPlayedSongViewModel @Inject constructor(
    getPlayedTracksUseCase: GetPlayedTracksUseCase
): ViewModel() {

    private val _playedSongs: Flow<List<PlayedTrackDataModel>> = getPlayedTracksUseCase()
    var playedSongs: Flow<List<PlayedTrackModelUI>> = _playedSongs.map { track ->
        track.map { it.toDataUI() }
    }


    init {

    }

}