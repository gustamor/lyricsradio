package net.laenredadera.app.android.lyricsradio.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import net.laenredadera.app.android.lyricsradio.domain.GetAlbumMbIDUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetAlbumNameFromMbIDUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetInsertPlayedTrackUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetItemArtistNameUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetItemSongMbIDUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetItemSongTitleUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetPlayedTracksUseCase
import net.laenredadera.app.android.lyricsradio.domain.model.PlayedTrackDataModel
import net.laenredadera.app.android.lyricsradio.domain.model.toDataUI
import net.laenredadera.app.android.lyricsradio.ui.model.PlayedTrackModelUI
import javax.inject.Inject

@HiltViewModel
class PreviousPlayedSongViewModel @Inject constructor(
    getPlayedTracksUseCase: GetPlayedTracksUseCase,
    private val getInsertPlayedTrackUseCase: GetInsertPlayedTrackUseCase,
    private val getItemSongTitleUseCase: GetItemSongTitleUseCase,
    private val getItemArtistNameUseCase: GetItemArtistNameUseCase,
    private val getAlbumMbIDUseCase: GetAlbumMbIDUseCase,
    private val getItemSongMbIDUseCase: GetItemSongMbIDUseCase,
    private val getAlbumNameFromMbIDUseCase: GetAlbumNameFromMbIDUseCase

    ): ViewModel() {

    private val _playedSongs: Flow<List<PlayedTrackDataModel>> = getPlayedTracksUseCase()
    var playedSongs: Flow<List<PlayedTrackModelUI>> = _playedSongs.map { track ->
        track.map { it.toDataUI() }
    }

    fun addPlayedSong(artist: String, title: String) {
        viewModelScope.launch {
            delay(1000)


            Log.i("GusMor addplayedsong", artist + title)
            val albumMbid: String = getAlbumMbIDUseCase(artist, title)
            delay(1000)

            val songMbID: String = getItemSongMbIDUseCase(artist, title)

            delay(1000)
            val album: String = getAlbumNameFromMbIDUseCase(albumMbid);
            // val cover: String = getAlbumCoverUseCase (artist, title);

            val trackModel = PlayedTrackDataModel(songMbID, title, artist, album, "", "", "")

            getInsertPlayedTrackUseCase(trackModel)
        }
    }

}